package org.example.entity;

import org.apache.poi.ss.usermodel.Row;

public class Employee {
    private long id;
    private String email;
    private String phone;
    private String address;
    private BankAccount bankAccount;

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }
}
