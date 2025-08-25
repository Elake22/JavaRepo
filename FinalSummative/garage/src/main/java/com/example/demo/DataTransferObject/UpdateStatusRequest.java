package com.example.demo.DataTransferObject;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateStatusRequest {
    @NotBlank
    private String status; // REQUESTED, CONFIRMED, IN_PROGRESS, COMPLETED, CANCELED
}
