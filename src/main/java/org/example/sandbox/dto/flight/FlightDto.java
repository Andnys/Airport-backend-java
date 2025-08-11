package org.example.sandbox.dto.flight;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.ZonedDateTime;

public class FlightDto {
    private final long id;
    private final String airline;
    private final String flightNumber;
    private final char domInt;
    private final ZonedDateTime scheduleTime;
    private final char arrDep;
    private final String airport;
    private final String viaAirport;
    private final String checkIn;
    private final String gate;
    private final Character statusCode;
    private final ZonedDateTime statusTime;
    private final String beltNumber;

    public FlightDto(
        long id,
        String airline,
        String flightId,
        char domInt,
        ZonedDateTime scheduleTime,
        char arrDep,
        String airport,
        String viaAirport,
        String checkIn,
        String gate,
        Character statusCode,
        ZonedDateTime statusTime,
        String beltNumber
    )
    {
        this.id = id;
        this.airline = airline;
        this.flightNumber = flightId;
        this.domInt = domInt;
        this.scheduleTime = scheduleTime;
        this.arrDep = arrDep;
        this.airport = airport;
        this.viaAirport = viaAirport;
        this.checkIn = checkIn;
        this.gate = gate;
        this.statusCode = statusCode;
        this.statusTime = statusTime;
        this.beltNumber = beltNumber;
    }

    public long getId() {return id;}
    public String getAirline() {return airline;}
    public String getFlightNumber() {return flightNumber;}
    public char getDomInt() {return domInt;}
    public String getScheduleTime() {return scheduleTime.toString();}
    @JsonIgnore
    public ZonedDateTime getScheduleTimeRaw() {return scheduleTime;}
    public char getArrDep() {return arrDep;}
    public String getAirport() {return airport;}

    public String getViaAirport() {return viaAirport;}
    public String getCheckIn() {return checkIn;}
    public String getGate() {return gate;}
    public Character getStatusCode() {return statusCode;}
    public String getStatusTime() { return statusTime != null ? statusTime.toString() : null;}
    @JsonIgnore
    public ZonedDateTime getStatusTimeRaw() {return statusTime;}
    public String getBeltNumber() {return beltNumber;}
}
