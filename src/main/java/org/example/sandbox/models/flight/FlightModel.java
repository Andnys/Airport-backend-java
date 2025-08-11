package org.example.sandbox.models.flight;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.example.sandbox.models.airline.AirlineModel;
import org.example.sandbox.models.airport.AirportModel;
import org.example.sandbox.serializers.ZonedDateTimeSerializer;

import java.time.ZonedDateTime;
import java.util.List;

public class FlightModel {
    private final long id;
    private final AirlineModel airline;
    private final String flightNumber;
    private final FlightAreaEnum area;
    @JsonSerialize(using= ZonedDateTimeSerializer.class)
    private final ZonedDateTime scheduleTime;
    private final FlightDirectionEnum direction;
    private final AirportModel airport;
    private final AirportModel OrgDest;
    private final List<AirportModel> viaAirports;
    private final String checkIn;
    private final String gate;
    private final FlightStatusModel status;
    private final String beltNumber;

    public FlightModel(
        long id,
        AirlineModel airline,
        String flightNumber,
        FlightAreaEnum area,
        ZonedDateTime scheduleTime,
        FlightDirectionEnum direction,
        AirportModel airport,
        AirportModel orgDest,
        List<AirportModel> viaAirports,
        String checkIn,
        String gate,
        FlightStatusModel status,
        String beltNumber
    )
    {
        this.id = id;
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.area = area;
        this.scheduleTime = scheduleTime;
        this.direction = direction;
        this.airport = airport;
        this.OrgDest = orgDest;
        this.viaAirports = viaAirports;
        this.checkIn = checkIn;
        this.gate = gate;
        this.status = status;
        this.beltNumber = beltNumber;
    }

    public long getId() {
        return id;
    }

    public AirlineModel getAirline() {
        return airline;
    }
    public String getFlightNumber() {
        return flightNumber;
    }
    public FlightAreaEnum getArea() {
        return area;
    }
    public ZonedDateTime getScheduleTime() {
        return scheduleTime;
    }
    public FlightDirectionEnum getDirection() {
        return direction;
    }
    public AirportModel getAirport() {
        return airport;
    }
    public AirportModel getOrgDest() {
        return OrgDest;
    }
    public List<AirportModel> getViaAirports() {
        return viaAirports;
    }
    public String getCheckIn() {
        return checkIn;
    }
    public String getGate() {
        return gate;
    }
    public FlightStatusModel getStatus() {
        return status;
    }
    public String getBeltNumber() {
        return beltNumber;
    }
}
