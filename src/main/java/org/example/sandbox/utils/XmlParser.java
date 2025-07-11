package org.example.sandbox.utils;

import org.example.sandbox.dto.airline.AirlineDto;
import org.example.sandbox.dto.airport.AirportDto;
import org.example.sandbox.dto.flight.FlightDto;
import org.w3c.dom.Element;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class XmlParser {
    public static AirportDto parseAirport(Element element) {
        String code = element.getAttribute("code");
        String name = element.getAttribute("name");

        return new AirportDto(code, name);
    }

    public static AirlineDto parseAirline(Element element) {
        String code = element.getAttribute("code");
        String name = element.getAttribute("name");
        return new AirlineDto(code, name);
    }

    public static FlightDto parseFlight(Element element) throws ParseException {

        long id = Long.parseLong(element.getAttribute("uniqueID"));
        String airline = getString(element,"airline");
        String flightNumber = getString(element, "flight_id");
        char domInt = getChar(element, "dom_int");
        ZonedDateTime scheduleTime = getZonedDateTime(element, "schedule_time");
        char arrDep = getChar(element,"arr_dep");
        String airport = getString(element,"airport");
        boolean delayed =  Boolean.parseBoolean(element.getElementsByTagName("delayed").item(0).getTextContent());

        return new FlightDto(id, airline, flightNumber, domInt, scheduleTime, arrDep, airport, null, null, null, null, null, null);
    }

    private static String getString(Element element, String tagName){
        if (element == null){
            return null;
        }
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }

    /*
    This returns a character, it should not be null.
     */
    private static char getChar(Element element, String tagName){
        String value = getString(element, tagName);
        return value.charAt(0);
    }

    private static Boolean  getBoolean(Element element, String tagName){
        if (element == null){
            return null;
        }
        String value = getString(element, tagName);
        return Boolean.parseBoolean(value);
    }

    private static ZonedDateTime getZonedDateTime(Element element, String tagName){
        if(element == null){
            return null;
        }
        String value = getString(element, tagName);
        try {
            return ZonedDateTime.parse(value, DateTimeFormatter.ISO_DATE_TIME);
        }
        catch (DateTimeParseException dtpe){
            return null;
        }
    }

    private static Long getLong(Element element, String tagName) {
        if (element == null) {
            return null;
        }
        try {
            long id = Long.parseLong(element.getElementsByTagName(tagName).item(0).getTextContent());
            return id;
        } catch (NumberFormatException nfe) {
            return null;
        }

    }
}
