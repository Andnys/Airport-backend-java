package org.example.sandbox.models.flight;

import java.time.ZonedDateTime;

public class FlightStatusModel {
    private final StatusCodeEnum code;
    private final ZonedDateTime time;

    public FlightStatusModel(StatusCodeEnum code, ZonedDateTime time) {
        this.code = code;
        this.time = time;
    }

    public StatusCodeEnum getCode() {
        return code;
    }
    public ZonedDateTime getTime() {
        return time;
    }
}
