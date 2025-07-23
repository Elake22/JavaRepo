package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TestData {
    public static PatientVisit[] getPatientVisits() {
        PatientVisit[] result = new PatientVisit[3];

        PatientVisit visit = new PatientVisit();

        visit.setPatientId("ABC123");

        visit.setPatientFirstName("Jacob");
        visit.setPatientLastName("McCandles");
        visit.setPatientAddress1("123 Main Street");
        visit.setPatientAddress2("#2");
        visit.setPatientCity("McCandles");
        visit.setPatientState("TX");
        visit.setPatientZip("11111");
        visit.setAppointmentTime(LocalDateTime.of(2022, 1, 1, 11, 15));
        visit.setVisitCost(BigDecimal.valueOf(30.00));
        visit.setCostPaid(true);
        visit.setPrimaryDiagnosis("Stubbed toe");
        visit.setTreatmentPlan("Walk it off");
        visit.setProviderNotes(new String[2]);
        visit.getProviderNotes()[0] = "Note 1";
        visit.getProviderNotes()[1] = "Note 2";
        visit.setRelatedPrescriptions(new String[1]);
        visit.getRelatedPrescriptions()[0] = "Alleve";
        visit.setProviderId("ZXY21");
        visit.setProviderFirstName("Pat");
        visit.setProviderLastName("Kilroy");
        visit.setProviderTitle("Dr");
        visit.setProviderSuffix("");

        result[0] = visit;

        visit = new PatientVisit();

        visit.setPatientId("ABC123");

        visit.setPatientFirstName("Jacob");
        visit.setPatientLastName("McCandles");
        visit.setPatientAddress1("123 Main Street");
        visit.setPatientAddress2("#2");
        visit.setPatientCity("McCandles");
        visit.setPatientState("TX");
        visit.setPatientZip("11111");
        visit.setAppointmentTime(LocalDateTime.of(2022, 2, 2, 10, 30));
        visit.setVisitCost(BigDecimal.valueOf(30.00));
        visit.setCostPaid(false);
        visit.setPrimaryDiagnosis("Bruised knee");
        visit.setTreatmentPlan("Stretching");
        visit.setProviderNotes(new String[2]);
        visit.getProviderNotes()[0] = "Note 1";
        visit.getProviderNotes()[1] = "Note 2";
        visit.setRelatedPrescriptions(new String[1]);
        visit.getRelatedPrescriptions()[0] = "Aspirin";
        visit.setProviderId("NEWID");
        visit.setProviderFirstName("New");
        visit.setProviderLastName("Nurse Practitioner");
        visit.setProviderTitle("NP");
        visit.setProviderSuffix("Jr");

        result[1] = visit;

        visit = new PatientVisit();

        visit.setPatientId("NOTJAKE");

        visit.setPatientFirstName("Barbara");
        visit.setPatientLastName("Jones");
        visit.setPatientAddress1("321 Main Street");
        visit.setPatientAddress2("#5");
        visit.setPatientCity("San Francisco");
        visit.setPatientState("CA");
        visit.setPatientZip("90210");
        visit.setAppointmentTime(LocalDateTime.of(2021, 5, 2, 15, 0));
        visit.setVisitCost(BigDecimal.valueOf(30.00));
        visit.setCostPaid(true);
        visit.setPrimaryDiagnosis("Stubbed toe");
        visit.setTreatmentPlan("Walk it off");
        visit.setProviderNotes(new String[2]);
        visit.getProviderNotes()[0] = "Note 1";
        visit.getProviderNotes()[1] = "Note 2";
        visit.setRelatedPrescriptions(new String[1]);
        visit.getRelatedPrescriptions()[0] = "Alleve";
        visit.setProviderId("ZXY21");
        visit.setProviderFirstName("Pat");
        visit.setProviderLastName("Kilroy");
        visit.setProviderTitle("Dr");
        visit.setProviderSuffix("");

        result[2] = visit;

        return result;
    }

    public static PatientVisit getExamplePatientVisit() {
        PatientVisit result = new PatientVisit();

        result.setPatientId("ABC123");

        result.setPatientFirstName("Jacob");
        result.setPatientLastName("McCandles");
        result.setPatientAddress1("123 Main Street");
        result.setPatientAddress2("#2");
        result.setPatientCity("McCandles");
        result.setPatientState("TX");
        result.setPatientZip("11111");
        result.setAppointmentTime(LocalDateTime.of(2022, 1, 1, 11, 15));
        result.setVisitCost(BigDecimal.valueOf(30.00));
        result.setCostPaid(true);
        result.setPrimaryDiagnosis("Stubbed toe");
        result.setTreatmentPlan("Walk it off");
        result.setProviderNotes(new String[2]);
        result.getProviderNotes()[0] = "Note 1";
        result.getProviderNotes()[1] = "Note 2";
        result.setRelatedPrescriptions(new String[1]);
        result.getRelatedPrescriptions()[0] = "Alleve";
        result.setProviderId("ZXY21");
        result.setProviderFirstName("Pat");
        result.setProviderLastName("Kilroy");
        result.setProviderTitle("Dr");
        result.setProviderSuffix("");

        return result;
    }
}
