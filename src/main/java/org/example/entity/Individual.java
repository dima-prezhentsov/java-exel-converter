package org.example.entity;

import org.apache.poi.ss.usermodel.Row;

public class Individual extends Employee {
    private String firstName;
    private String lastName;
    private boolean hasChildren;
    private int age;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public int getAge() {
        return age;
    }
}
