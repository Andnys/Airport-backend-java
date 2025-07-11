package org.example.sandbox.models.flight;

import org.example.sandbox.models.airline.AirlineModel;
import org.example.sandbox.models.airport.AirportModel;

import javax.naming.AuthenticationNotSupportedException;
import java.time.ZonedDateTime;
import java.util.List;

public class FlightModel {
    private final long id;
    private AirlineModel airline;
    private String flightNumber;
    private FlightAreaEnum area;
    private ZonedDateTime scheduleTime;
    private FlightDirectionEnum direction;
    private AirportModel airport;
    private AirportModel OrgDest;
    private List<AirportModel> viaAirports;
    private String checkIn;
    private String gate;
    private FlightStatusModel status;
    private String beltNumber;

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

    public boolean isDelayed() throws AuthenticationNotSupportedException {
        throw new AuthenticationNotSupportedException("This is not implemented yet");
    }
}
