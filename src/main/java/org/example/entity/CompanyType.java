package org.example.entity;

public enum CompanyType {
    SARS,
    SARL;

    public static CompanyType fromString(String value) {
        return switch (value) {
            case "SARS" -> CompanyType.SARS;
            case "SARL" -> CompanyType.SARL;
            default -> throw new RuntimeException("Not implemented");
        };
    }
}
