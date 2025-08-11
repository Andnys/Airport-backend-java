package org.example.sandbox.models.flight;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.example.sandbox.serializers.ZonedDateTimeSerializer;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class FlightsModel {
    private final ArrayList<FlightModel> flights;
    @JsonSerialize(using=ZonedDateTimeSerializer.class)
    private final ZonedDateTime lastUpdated;
    private final String airport;

    public FlightsModel(Iterable<FlightModel> flights, ZonedDateTime lastUpdated, String airport)
    {
        this.flights = new ArrayList<>();
        for(FlightModel f : flights)
        {
            this.flights.add(f);
        }
        this.lastUpdated = lastUpdated;
        this.airport = airport;
    }

    public FlightsModel() {
        this.airport = "";
        this.flights = new ArrayList<>();
        this.lastUpdated = ZonedDateTime.now();
    }

    public ArrayList<FlightModel> getFlights() { return this.flights; }
    public ZonedDateTime getLastUpdated() { return this.lastUpdated; }
    public String getAirport() { return this.airport; }
}
