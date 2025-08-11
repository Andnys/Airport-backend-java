package org.example.sandbox.models.flight;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.example.sandbox.serializers.ZonedDateTimeSerializer;

import java.time.ZonedDateTime;

public class FlightStatusModel {
    private final StatusCodeEnum code;
    @JsonSerialize(using= ZonedDateTimeSerializer.class)
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
