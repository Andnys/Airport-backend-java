package org.example.sandbox.dto;

public abstract class AbstractCodeAndNameDto {
    private final String code;
    private final String name;

    public AbstractCodeAndNameDto(String code, String name) {
        this.name = name;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
