package org.example.sandbox.controllers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.example.sandbox.utils.HttpService;
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

@Path("/flight")
public class Flights {
    @GET
    @Path("/list/{airport}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FlightDto> getAll(@PathParam("airport") String airport) {
        try {
            String response = HttpService.get("https://asrv.avinor.no/XmlFeed/v1.0?TimeFrom=1&TimeTo=7&airport=" + airport.toUpperCase() + "&direction=D&lastUpdate=2024-08-08T09:30:00Z");
            List<FlightDto> flights = new ArrayList<>();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(response));

            Document doc = builder.parse(is);

            doc.getDocumentElement().normalize();

            Element flightsNode = (Element) doc.getElementsByTagName("flights").item(0);
            String lastUpdate = flightsNode.getAttribute("lastUpdate");
            NodeList nodeList = flightsNode.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                flights.add(XmlParser.parseFlight((Element) nodeList.item(i)));
            }
            return flights;
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
