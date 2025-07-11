package org.example.sandbox.dto.flight;

import java.util.Calendar;

public class FlightStatus {
    private final char code;
    private final Calendar time;

    public FlightStatus(char code, Calendar time) {
        this.code = code;
        this.time = time;
    }

    public char getCode() {
        return code;
    }

    public Calendar getTime() {
        return time;
    }
}
