package org.example.sandbox.Factories;


import org.example.sandbox.dto.airline.AirlineDto;

import org.example.sandbox.models.airline.AirlineModel;

public class AirlineFactory implements IEntityFactory<AirlineDto, AirlineModel> {

    @Override
    public AirlineModel createModel(AirlineDto dto) {
        return new AirlineModel(dto.getCode(), dto.getName());
    }
}
