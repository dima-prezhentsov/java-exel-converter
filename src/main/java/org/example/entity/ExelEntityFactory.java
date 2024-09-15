package org.example.entity;

public class ExelEntityFactory {
    public static ExcelEntity createEntity(String type) {
        return switch (type) {
            case "Individual" -> new Individual();
            case "Company" -> new Company();
            default -> throw new RuntimeException("Not implemented");
        };
    }
}
