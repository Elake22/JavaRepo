package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {
    Utility util = new Utility();
    PatientVisit samplePatientVisit;
    PatientVisit[] visitList;

    @BeforeEach
    void setUp() {
        samplePatientVisit = TestData.getExamplePatientVisit();
        visitList = TestData.getPatientVisits();
    }


    @Test
    void formatDataTime() {
        LocalDateTime in = LocalDateTime.of(1976, 1, 29, 10, 28);
        String expected = "01/29/1976 10:28";

        assertEquals(expected, util.formatDateTime(in));

    }

    @Test
    void formatPatientForListOutput() {
        String actual = util.formatPatientForListOutput(samplePatientVisit);
        String expected = "01/01/2022 11:15 - McCandles, Jacob for Dr Kilroy - 30.0: PAID";
        assertEquals(expected, actual);
    }

    @Test
    void calculateTotalOutstandingPayments() {
        BigDecimal expected = BigDecimal.valueOf(30.00);
        assertEquals(expected, util.calculateTotalOutstandingPayments(TestData.getPatientVisits()));
    }

    @Test
    void calculateTotalReceived() {
        BigDecimal expected = BigDecimal.valueOf(60.00);
        assertEquals(expected, util.calculateTotalReceived(TestData.getPatientVisits()));
    }

    @Test
    void getVisitsByProviderId() {
        int expectedSize = 2;
        assertEquals(expectedSize, util.getVisitsByProviderId("ZXY21", TestData.getPatientVisits()).length);
    }

    @Test
    void getVisitsByPatientId() {
        int expectedSize = 2;
        assertEquals(expectedSize, util.getVisitsByPatientId("ABC123", TestData.getPatientVisits()).length);
    }
}