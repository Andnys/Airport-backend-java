package org.example.sandbox.services;

import org.example.sandbox.Factories.FlightFactory;
import org.example.sandbox.dto.flight.FlightDto;
import org.example.sandbox.models.flight.FlightDirectionEnum;
import org.example.sandbox.models.flight.FlightModel;
import org.example.sandbox.models.flight.FlightsModel;
import org.example.sandbox.utils.StringUtils;
import org.example.sandbox.utils.XmlParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlightService  {

    public static FlightsModel getAll(String airport) {
        return getAll(airport, null, null);
    }


    public static FlightsModel getAll(String airport, ZonedDateTime scheduleTime) {
        return getAll(airport, scheduleTime, null);
    }


    public static FlightsModel getAll(String airport, ZonedDateTime lastUpdated, FlightDirectionEnum direction) {
        if (StringUtils.isNullOrEmpty(airport)) {
            return new FlightsModel();
        }
        try {
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("?airport=").append(airport.toUpperCase());
            if(lastUpdated != null) {
                queryBuilder.append("&lastUpdate=").append(lastUpdated.withZoneSameInstant(ZoneOffset.UTC)
                        .format(DateTimeFormatter.ISO_INSTANT));
            }
            if(direction != null) {
                queryBuilder.append("&direction=").append(direction.name());
            }
            queryBuilder.append("&timeFrom=1&timeTo=7");

            String response = HttpService.get("https://asrv.avinor.no/XmlFeed/v1.0" + queryBuilder);

            ArrayList<FlightDto> flightDtos = new ArrayList<>();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(response));

            Document doc = builder.parse(is);

            doc.getDocumentElement().normalize();

            Element flightsNode = (Element) doc.getElementsByTagName("flights").item(0);
            String lastUpdateString = flightsNode.getAttribute("lastUpdate");
            ZonedDateTime lastUpdate = ZonedDateTime.parse(lastUpdateString, DateTimeFormatter.ISO_DATE_TIME);
            NodeList nodeList = flightsNode.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                flightDtos.add(XmlParser.parseFlight((Element) nodeList.item(i)));
            }
            FlightDto[] dtoArray = flightDtos.toArray(new FlightDto[0]);
            List<FlightModel> flightModels = Arrays.stream(dtoArray)
                    .map(a -> FlightFactory.Create(a, airport)).toList();

            return new FlightsModel(flightModels, lastUpdate, airport);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
