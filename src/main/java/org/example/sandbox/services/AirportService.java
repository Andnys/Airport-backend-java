package org.example.sandbox.services;

import org.example.sandbox.dto.airport.AirportDto;
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
import java.util.ArrayList;
import java.util.List;

public class AirportService {
    private static final String baseUrl = "https://asrv.avinor.no/airportNames/v1.0";

    public static AirportDto get(String code) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(baseUrl);
        urlBuilder.append("?");
        urlBuilder.append("airport=").append(code);

        try {
            String response = HttpService.get(urlBuilder.toString());

            NodeList nodeList = extractNodeList(response);

            return XmlParser.parseAirport((Element) nodeList.item(0));
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<AirportDto> getAll() {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(baseUrl);

        try {
            String response = HttpService.get(urlBuilder.toString());

            List<AirportDto> airports = new ArrayList<>();

            NodeList nodeList = extractNodeList(response);
            for (int i = 0; i < nodeList.getLength(); i++) {
                airports.add(XmlParser.parseAirport((Element) nodeList.item(i)));
            }
            return airports;
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    private static NodeList extractNodeList(String response) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(response));

        Document doc = builder.parse(is);

        doc.getDocumentElement().normalize();

        return doc.getElementsByTagName("airportName");
    }
}
