package org.example.sandbox.controllers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.example.sandbox.dto.airport.AirportDto;
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

@Path("/airport")
public class Airports {

    @GET
    @Path("get-all")
    @Produces("application/json")
    public List<AirportDto> getAll() {
        try {
            String response = HttpService.get("https://asrv.avinor.no/airportNames/v1.0");
            List<AirportDto> airports = new ArrayList<>();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(response));

            Document doc = builder.parse(is);

            doc.getDocumentElement().normalize();

            NodeList airportList = doc.getElementsByTagName("airportName");
            for (int i = 0; i < airportList.getLength(); i++) {
                airports.add(XmlParser.parseAirport((Element) airportList.item(i)));
            }
            return airports;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return new ArrayList<AirportDto>();
    }

}
