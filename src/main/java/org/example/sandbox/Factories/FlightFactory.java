package org.example.sandbox.Factories;

import org.example.sandbox.dto.airline.AirlineDto;
import org.example.sandbox.dto.airport.AirportDto;
import org.example.sandbox.dto.flight.FlightDto;
import org.example.sandbox.models.airline.AirlineModel;
import org.example.sandbox.models.airport.AirportModel;
import org.example.sandbox.models.flight.*;
import org.example.sandbox.services.AirlineService;
import org.example.sandbox.services.AirportService;
import org.example.sandbox.utils.StringUtils;

import java.util.ArrayList;

public class FlightFactory {
    public static FlightModel Create(FlightDto dto, String orgDest) {
        AirlineFactory airlineFactory = new AirlineFactory();
        AirportFactory airportFactory = new AirportFactory();

        AirlineDto airlineDto = AirlineService.get(dto.getAirline());
        AirportDto airportDto = AirportService.get(dto.getAirport());
        AirportDto orgDestDto = AirportService.get(orgDest);

        AirlineModel airlineModel = airlineFactory.createModel(airlineDto);
        AirportModel airportModel = airportFactory.createModel(airportDto);
        AirportModel orgDestModel = airportFactory.createModel(orgDestDto);

        try {
            FlightAreaEnum areaEnum = FlightAreaEnum.valueOf(String.valueOf(dto.getDomInt()));
            FlightDirectionEnum directionEnum = FlightDirectionEnum.valueOf(String.valueOf(dto.getArrDep()));

            FlightStatusModel flightStatusModel;
            if(dto.getStatusCode() == null){
                flightStatusModel = null;
            }
            else {
                StatusCodeEnum statusCodeEnum = StatusCodeEnum.valueOf(String.valueOf(dto.getStatusCode()));
                flightStatusModel = new FlightStatusModel(statusCodeEnum, dto.getStatusTimeRaw());
            }

            ArrayList<AirportModel> viaAirports = getViaAirports(dto.getViaAirport());


            return new FlightModel(
                dto.getId(),
                airlineModel,
                dto.getFlightNumber(),
                areaEnum,
                dto.getScheduleTimeRaw(),
                directionEnum,
                airportModel,
                orgDestModel,
                viaAirports,
                dto.getCheckIn(),
                dto.getGate(),
                flightStatusModel,
                dto.getBeltNumber()
            );
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    private static ArrayList<AirportModel> getViaAirports(String viaAirportsString) {
        if(StringUtils.isNullOrEmpty(viaAirportsString)){
            return new ArrayList<>();
        }
        AirportFactory airportFactory = new AirportFactory();
        String[] codes = viaAirportsString.split(",");
        ArrayList<AirportModel> viaAirports = new ArrayList<>();
        for (int i = 0; i < codes.length; i++) {
            try {
                String code = codes[i];
                if(StringUtils.isNullOrEmpty(code)){
                    continue;
                }
                AirportDto airportdto = AirportService.get(code);
                AirportModel  airportModel = airportFactory.createModel(airportdto);
                viaAirports.add(airportModel);
            }
            catch (IllegalArgumentException e) {
                return viaAirports;
            }
        }
        return viaAirports;
    }
}
