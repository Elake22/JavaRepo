package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utility {
    public String formatDateTime(LocalDateTime dt) {
        return dt.format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm"));
    }

    public String formatPatientForListOutput(PatientVisit patientVisit) {
        StringBuilder result = new StringBuilder();

        result.append(formatDateTime(patientVisit.getAppointmentTime())).append(" - ");
        result.append(patientVisit.getPatientLastName()).append(", ").append(patientVisit.getPatientFirstName()).append(" ");
        result.append("for ").append(patientVisit.getProviderTitle()).append(" ").append(patientVisit.getProviderLastName()).append(" - ");
        result.append(patientVisit.getVisitCost()). append(": ").append(patientVisit.isCostPaid() ? "PAID" : "DUE");

        return result.toString();
    }

    public BigDecimal calculateTotalOutstandingPayments(PatientVisit[] visits) {
        BigDecimal result = new BigDecimal(0);

        for (PatientVisit pv : visits) {
            if (!pv.isCostPaid()) {
                result = result.add(pv.getVisitCost());
            }
        }

        return result;
    }

    public BigDecimal calculateTotalReceived(PatientVisit[] visits) {
        BigDecimal result = new BigDecimal(0);

        for (PatientVisit pv : visits) {
            if (pv.isCostPaid()) {
                result = result.add(pv.getVisitCost());
            }
        }

        return result;
    }

    public PatientVisit[] getVisitsByProviderId(String providerId, PatientVisit[] visits) {
        PatientVisit[] collector = new PatientVisit[visits.length];
        int nextCollectorIndex = 0;

        for (PatientVisit pv : visits) {
            if (pv.getProviderId().equals(providerId)) {
                collector[nextCollectorIndex] = pv;
                nextCollectorIndex++;
            }
        }

        PatientVisit[] results = new PatientVisit[nextCollectorIndex];
        for (int i = 0; i < nextCollectorIndex; i++) {
            results[i] = collector[i];
        }
        return results;
    }

    public PatientVisit[] getVisitsByPatientId(String patientId, PatientVisit[] visits) {
        PatientVisit[] collector = new PatientVisit[visits.length];
        int nextCollectorIndex = 0;

        for (PatientVisit pv : visits) {
            if (pv.getPatientId().equals(patientId)) {
                collector[nextCollectorIndex] = pv;
                nextCollectorIndex++;
            }
        }

        PatientVisit[] results = new PatientVisit[nextCollectorIndex];
        for (int i = 0; i < nextCollectorIndex; i++) {
            results[i] = collector[i];
        }
        return results;
    }

}
