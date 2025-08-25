// src/main/java/com/example/demo/config/ApiExceptionHandler.java
package com.example.demo.config;

import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler {

    private ResponseEntity<Map<String, Object>> build(HttpStatus status, String message, WebRequest req) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", OffsetDateTime.now().toString());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", (message == null || message.isBlank()) ? "Unexpected error" : message);
        if (req instanceof ServletWebRequest swr) {
            body.put("path", swr.getRequest().getRequestURI());
        }
        return ResponseEntity.status(status).body(body);
    }

    /** Bean validation on @RequestBody (e.g., @Valid Booking) */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgNotValid(MethodArgumentNotValidException ex, WebRequest req) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + " " + fe.getDefaultMessage())
                .collect(Collectors.joining("; "));
        if (msg.isBlank()) msg = "Validation failed.";
        return build(HttpStatus.BAD_REQUEST, msg, req);
    }

    /** Bean validation on method params (e.g., @Validated service methods) */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraint(ConstraintViolationException ex, WebRequest req) {
        String msg = ex.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + " " + v.getMessage())
                .collect(Collectors.joining("; "));
        if (msg.isBlank()) msg = "Validation failed.";
        return build(HttpStatus.BAD_REQUEST, msg, req);
    }

    /** Malformed JSON / wrong types */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleNotReadable(HttpMessageNotReadableException ex, WebRequest req) {
        return build(HttpStatus.BAD_REQUEST, "Malformed JSON request.", req);
    }

    /** Explicit guards (e.g., past date) */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArg(IllegalArgumentException ex, WebRequest req) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage(), req);
    }

    /** DB conflicts like duplicate email */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrity(DataIntegrityViolationException ex, WebRequest req) {
        String raw = ex.getMostSpecificCause() != null ? ex.getMostSpecificCause().getMessage() : ex.getMessage();
        String lower = raw != null ? raw.toLowerCase() : "";
        String msg = (lower.contains("customers.email") || lower.contains("unique"))
                ? "A customer with that email already exists."
                : "Data conflict.";
        return build(HttpStatus.CONFLICT, msg, req); // 409
    }

    /** Last-resort safety net */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex, WebRequest req) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error.", req);
    }
    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<Map<String, String>> handleTx(TransactionSystemException ex) {
        Throwable root = ex.getMostSpecificCause(); // deepest cause
        String msg = (root != null ? root.getMessage() : ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", msg != null ? msg : "Transaction failed"));
    }
}

