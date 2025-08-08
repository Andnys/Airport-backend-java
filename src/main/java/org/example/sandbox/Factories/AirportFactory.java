package org.example.sandbox.Factories;

import org.example.sandbox.dto.airport.AirportDto;
import org.example.sandbox.models.airport.AirportModel;

public class AirportFactory implements IEntityFactory<AirportDto, AirportModel> {
    @Override
    public AirportModel createModel(AirportDto dto) {
        return new AirportModel(dto.getCode(), dto.getName());
    }
}
