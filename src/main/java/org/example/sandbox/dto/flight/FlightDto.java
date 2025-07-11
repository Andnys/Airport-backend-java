package org.example.sandbox.dto.flight;

import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Optional;

public class FlightDto {
    private final long id;
    private final String airline;
    private final String flightNumber;
    private final char domInt;
    private final ZonedDateTime scheduleTime;
    private final char arrDep;
    private final String airport;
    private final Optional<String> checkIn;
    private final Optional<String> gate;
    private final Optional<Character> statusCode;
    private final Optional<ZonedDateTime> statusTime;
    private final Optional<String> beltNumber;

    public FlightDto(
        long id,
        String airline,
        String flightId,
        char domInt,
        ZonedDateTime scheduleTime,
        char arrDep,
        String airport,
        Optional<String> viaAirport,
        Optional<String> checkIn,
        Optional<String> gate,
        Optional<Character> statusCode,
        Optional<ZonedDateTime> statusTime,
        Optional<String> beltNumber
    )
    {
        this.id = id;
        this.airline = airline;
        this.flightNumber = flightId;
        this.domInt = domInt;
        this.scheduleTime = scheduleTime;
        this.arrDep = arrDep;
        this.airport = airport;
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
    public ZonedDateTime getScheduleTime() {return scheduleTime;}
    public char getArrDep() {return arrDep;}
    public String getAirport() {return airport;}

    public Optional<String> getCheckIn() {return checkIn;}
    public Optional<String> getGate() {return gate;}
    public Optional<Character> getStatusCode() {return statusCode;}
    public Optional<ZonedDateTime> getStatusTime() {return statusTime;}
    public Optional<String> getBeltNumber() {return beltNumber;}
}
