package org.example.sandbox.dto.airport;

public class AirportDto {

    private final String code;
    private final String name;

    public AirportDto(String code, String name) {
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
