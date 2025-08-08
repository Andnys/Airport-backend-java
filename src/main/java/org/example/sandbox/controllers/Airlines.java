package org.example.sandbox.controllers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.example.sandbox.dto.airline.AirlineDto;
import org.example.sandbox.services.AirlineService;
import org.example.sandbox.services.HttpService;
import org.example.sandbox.utils.XmlParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Path("/airline")
public class Airlines {

    @GET
    @Path("get-all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AirlineDto> getAll() {
        try {
            return AirlineService.getAll();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return new ArrayList<AirlineDto>();
    }

}

