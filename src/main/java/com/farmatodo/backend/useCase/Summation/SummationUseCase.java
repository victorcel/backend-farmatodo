package com.farmatodo.backend.useCase.Summation;

import com.farmatodo.backend.dto.ResponseDTO;

public class SummationUseCase implements SummationInterface {
    @Override
    public ResponseDTO calculate(Long number) throws Exception {
        ResponseDTO response = new ResponseDTO();
        response.setResult((number * (number + 1)) / 2);
        return response;
    }
}
