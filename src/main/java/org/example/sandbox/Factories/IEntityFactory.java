package org.example.sandbox.Factories;

import org.example.sandbox.dto.AbstractCodeAndNameDto;
import org.example.sandbox.models.CodeAndNameModel;

public interface IEntityFactory<TIn extends AbstractCodeAndNameDto, TOut extends CodeAndNameModel> {
    TOut createModel(TIn dto);
}
