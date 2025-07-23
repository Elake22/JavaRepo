package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PatientVisit {
    private String patientId;
    private String patientFirstName;
    private String patientLastName;
    private String patientAddress1;
    private String patientAddress2;
    private String patientCity;
    private String patientState;
    private String patientZip;
    private LocalDateTime appointmentTime;
    private BigDecimal visitCost;
    private boolean costPaid;
    private String primaryDiagnosis;
    private String treatmentPlan;
    private String[] providerNotes;
    private String[] relatedPrescriptions;
    private String providerId;
    private String providerFirstName;
    private String providerLastName;
    private String providerTitle;
    private String providerSuffix;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientAddress1() {
        return patientAddress1;
    }

    public void setPatientAddress1(String patientAddress1) {
        this.patientAddress1 = patientAddress1;
    }

    public String getPatientAddress2() {
        return patientAddress2;
    }

    public void setPatientAddress2(String patientAddress2) {
        this.patientAddress2 = patientAddress2;
    }

    public String getPatientCity() {
        return patientCity;
    }

    public void setPatientCity(String patientCity) {
        this.patientCity = patientCity;
    }

    public String getPatientState() {
        return patientState;
    }

    public void setPatientState(String patientState) {
        this.patientState = patientState;
    }

    public String getPatientZip() {
        return patientZip;
    }

    public void setPatientZip(String patientZip) {
        this.patientZip = patientZip;
    }

    public BigDecimal getVisitCost() {
        return visitCost;
    }

    public void setVisitCost(BigDecimal visitCost) {
        this.visitCost = visitCost;
    }

    public boolean isCostPaid() {
        return costPaid;
    }

    public void setCostPaid(boolean costPaid) {
        this.costPaid = costPaid;
    }

    public String getPrimaryDiagnosis() {
        return primaryDiagnosis;
    }

    public void setPrimaryDiagnosis(String primaryDiagnosis) {
        this.primaryDiagnosis = primaryDiagnosis;
    }

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    public String[] getProviderNotes() {
        return providerNotes;
    }

    public void setProviderNotes(String[] providerNotes) {
        this.providerNotes = providerNotes;
    }

    public String[] getRelatedPrescriptions() {
        return relatedPrescriptions;
    }

    public void setRelatedPrescriptions(String[] relatedPrescriptions) {
        this.relatedPrescriptions = relatedPrescriptions;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderFirstName() {
        return providerFirstName;
    }

    public void setProviderFirstName(String providerFirstName) {
        this.providerFirstName = providerFirstName;
    }

    public String getProviderLastName() {
        return providerLastName;
    }

    public void setProviderLastName(String providerLastName) {
        this.providerLastName = providerLastName;
    }

    public String getProviderTitle() {
        return providerTitle;
    }

    public void setProviderTitle(String providerTitle) {
        this.providerTitle = providerTitle;
    }

    public String getProviderSuffix() {
        return providerSuffix;
    }

    public void setProviderSuffix(String providerSuffix) {
        this.providerSuffix = providerSuffix;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}
