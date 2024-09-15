package org.example.entity;

import org.apache.poi.ss.usermodel.Row;

public class Company extends Employee {
    private String name;
    private CompanyType type;


    @Override
    public void fromRow(Row row) {
        String name = row.getCell(9).getStringCellValue();
        CompanyType type = CompanyType.fromString(row.getCell(10).getStringCellValue());

        setName(name);
        setType(type);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompanyType getType() {
        return type;
    }

    public void setType(CompanyType type) {
        this.type = type;
    }
}
