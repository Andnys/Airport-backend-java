package org.example.sandbox.controllers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.example.sandbox.dto.airline.AirlineDto;
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

@Path("/airline")
public class Airlines {

    @GET
    @Path("get-all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AirlineDto> getAll() {
        try {
            String response = HttpService.get("https://asrv.avinor.no/airlineNames/v1.0");
            List<AirlineDto> airlines = new ArrayList<>();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(response));

            Document doc = builder.parse(is);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("airlineName");
            for (int i = 0; i < nodeList.getLength(); i++) {
                airlines.add(XmlParser.parseAirline((Element) nodeList.item(i)));
            }
            return airlines;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return new ArrayList<AirlineDto>();
    }

}

