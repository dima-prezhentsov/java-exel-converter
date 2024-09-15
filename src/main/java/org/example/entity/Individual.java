package org.example.entity;

import org.apache.poi.ss.usermodel.Row;

public class Individual extends Employee {
    private String firstName;
    private String lastName;
    private boolean hasChildren;
    private int age;

    @Override
    public void fromRow(Row row) {
        String firstName = row.getCell(5).getStringCellValue();
        String lastName = row.getCell(6).getStringCellValue();
        boolean hasChildren = row.getCell(7).getBooleanCellValue();
        int age = (int) row.getCell(8).getNumericCellValue();

        setFirstName(firstName);
        setLastName(lastName);
        setHasChildren(hasChildren);
        setAge(age);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
