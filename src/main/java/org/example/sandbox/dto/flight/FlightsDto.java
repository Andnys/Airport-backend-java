package org.example.sandbox.dto.flight;

import org.example.sandbox.controllers.Flights;

import java.time.ZonedDateTime;
import java.util.List;

public class FlightsDto {
    private final List<FlightDto> flights;
    private final ZonedDateTime lastUpdated;
    private final String Airport;

    public FlightsDto(List<FlightDto> flights, ZonedDateTime lastUpdated, String Airport) {
        this.flights = flights;
        this.lastUpdated = lastUpdated;
        this.Airport = Airport;
    }

    public String getLastUpdated() { return lastUpdated.toString(); }
    public String getAirport() { return Airport; }

    public List<FlightDto> getFlights() { return flights; }
}
