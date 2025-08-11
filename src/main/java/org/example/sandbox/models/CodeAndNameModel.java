package org.example.sandbox.models;

public class CodeAndNameModel {
    private final String code;
    private final String name;

    public CodeAndNameModel(String code, String name) {
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
