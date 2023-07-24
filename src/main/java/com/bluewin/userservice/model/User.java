package com.bluewin.userservice.model;

import javax.persistence.*;

// This class will represent the user in the application. For our purpose should at least contain fields for the user ID, username, monthlyCharge, and email quota.
// We will avoid fractional numbers for simplicity.
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private int emailQuota;

    @Column
    private int monthlyCharge;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getEmailQuota() {
        return emailQuota;
    }

    public void setEmailQuota(int emailQuota) {
        this.emailQuota = emailQuota;
    }

    public int getMonthlyCharge() { // Add this getter
        return monthlyCharge;
    }

    public void setMonthlyCharge(int monthlyCharge) { // Add this setter
        this.monthlyCharge = monthlyCharge;
    }

}
