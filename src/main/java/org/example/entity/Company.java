package org.example.entity;

import org.apache.poi.ss.usermodel.Row;

public class Company extends Employee {
    private String name;
    private CompanyType type;

    public String getName() {
        return name;
    }

    public CompanyType getType() {
        return type;
    }
}
