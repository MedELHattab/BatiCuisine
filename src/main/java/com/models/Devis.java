package com.models;

import java.sql.Date;

public class Devis {
    private double estimatedAmount ;
    private Date issueDate ;
    private Date validityDate ;
    private boolean accepted ;
    private int projectID ;

    public Devis( double estimatedAmount, Date issueDate, Date validityDate, int projectID ) {
        this.estimatedAmount = estimatedAmount;
        this.issueDate = issueDate;
        this.validityDate = validityDate;
        this.projectID = projectID;
    }

    public boolean setAccepted(boolean b) {
        return this.accepted = true;
    }

    public double getEstimatedAmount() {
        return estimatedAmount;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public Date getValidityDate() {
        return validityDate ;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public int getProjectID() {
        return projectID;
    }
}
