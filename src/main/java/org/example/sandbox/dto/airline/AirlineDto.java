package org.example.sandbox.dto.airline;

public class AirlineDto {
    private final String code;
    private final String name;

    public AirlineDto(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
}
