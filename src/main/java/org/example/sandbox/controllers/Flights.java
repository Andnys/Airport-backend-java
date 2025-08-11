package org.example.sandbox.controllers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.example.sandbox.models.flight.FlightsModel;
import org.example.sandbox.services.FlightService;

@Path("/flight")
public class Flights {
    @GET
    @Path("/list/{airport}")
    @Produces(MediaType.APPLICATION_JSON)
    public FlightsModel getAll(@PathParam("airport") String airport) {
        try {
            return FlightService.getAll(airport);
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
