package org.example.sandbox.utils;

import org.example.sandbox.dto.airline.AirlineDto;
import org.example.sandbox.dto.airport.AirportDto;
import org.example.sandbox.dto.flight.FlightDto;
import org.w3c.dom.Element;

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

    public static FlightDto parseFlight(Element element) {

        long id = Long.parseLong(element.getAttribute("uniqueID"));
        String airline = getString(element,"airline");
        String flightNumber = getString(element, "flight_id");
        char domInt = getChar(element, "dom_int");
        ZonedDateTime scheduleTime = getZonedDateTime(element, "schedule_time");
        char arrDep = getChar(element,"arr_dep");
        String airport = getString(element,"airport");
        String viaAirport =  getString(element,"via_airport");
        String checkin = getString(element,"check_in");
        String gate = getString(element,"gate");
        String belt = getString(element,"belt");

        ZonedDateTime statusTime = null;
        Character statusCodeChar = null;
        Element statusElement = (Element) element.getElementsByTagName("status").item(0);
        if(statusElement != null) {
            String statusCode = statusElement.getAttribute("code");
            statusTime = ZonedDateTime.parse(statusElement.getAttribute("time"));
            statusCodeChar = !StringUtils.isNullOrEmpty(statusCode) ? statusCode.charAt(0) : null;
        }
        return new FlightDto(id, airline, flightNumber, domInt, scheduleTime, arrDep, airport, viaAirport, checkin, gate, statusCodeChar, statusTime, belt);
    }

    private static String getString(Element element, String tagName){
        if (element.getElementsByTagName(tagName).getLength() < 1) {
            return null;
        }
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }

    /*
    This returns a character, it should not be null.
     */
    private static char getChar(Element element, String tagName) throws AssertionError{
        String value = getString(element, tagName);
        assert value != null;
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
        if(StringUtils.IsNullOrBlank(value)) {
            return null;
        }
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
            return Long.parseLong(element.getElementsByTagName(tagName).item(0).getTextContent());
        } catch (NumberFormatException nfe) {
            return null;
        }

    }
}
