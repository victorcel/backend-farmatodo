package com.farmatodo.backend.useCase.Summation;

import com.farmatodo.backend.dto.ResponseDTO;

public interface SummationInterface {
    ResponseDTO calculate(Long number) throws Exception;
}
