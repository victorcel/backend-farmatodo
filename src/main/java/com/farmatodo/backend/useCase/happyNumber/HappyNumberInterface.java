package com.farmatodo.backend.useCase.happyNumber;

import com.farmatodo.backend.dto.NumbersDTO;

public interface HappyNumberInterface {
    NumbersDTO calculateHappyNumbers(String numbers) throws Exception;
}
