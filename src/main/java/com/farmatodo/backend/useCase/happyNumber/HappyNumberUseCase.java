package com.farmatodo.backend.useCase.happyNumber;

import com.farmatodo.backend.dto.NumberDTO;
import com.farmatodo.backend.dto.NumbersDTO;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class HappyNumberUseCase implements HappyNumberInterface {

    @Override
    public NumbersDTO calculateHappyNumbers(String strNumbers) throws Exception {
        NumbersDTO response = new NumbersDTO();
        String[] numbers = strNumbers.split(",");
        Set<Long> happyNumber = new LinkedHashSet<>();
        Set<Long> unhappyNumbers = new LinkedHashSet<>();

        for (String item : numbers) {
            if (!this.isValidNumber(item))
                throw new Exception("'" + item + "' no es posible calcular si es feliz o no");

            Boolean isHappy;
            if (happyNumber.contains(Long.parseLong(item))) {
                isHappy = Boolean.TRUE;
            } else if (unhappyNumbers.contains(Long.parseLong(item))) {
                isHappy = Boolean.FALSE;
            } else {
                isHappy = this.isHappyNumber(Long.parseLong(item));
                if (isHappy) happyNumber.add(Long.parseLong(item));
                else unhappyNumbers.add(Long.parseLong(item));
            }

            NumberDTO number = new NumberDTO();
            number.setNumber(Long.parseLong(item));
            number.setIsHappy(isHappy);

            if (response.getNumbers() == null)
                response.setNumbers(new ArrayList<>());
            response.getNumbers().add(number);
        }

        return response;
    }

    private boolean isValidNumber(String item) {
        if (item.chars().allMatch(Character::isDigit)) {
            try {
                Long.parseLong(item);
                return true;
            } catch (NumberFormatException exc) {
                throw new RuntimeException(exc);
            }
        }
        return false;
    }

    private Boolean isHappyNumber(long number) {
        if (number == 1) return Boolean.TRUE;
        Set<Long> calculatedNumbers = new LinkedHashSet<>();

        while (!calculatedNumbers.contains(number)) {
            if (number == 1) return Boolean.TRUE;
            calculatedNumbers.add(number);
            long newNumber = 0;
            String strNumber = String.valueOf(number);
            for (Character character : strNumber.toCharArray()) {
                newNumber += (long) Math.pow(Character.getNumericValue(character), 2);
            }
            number = newNumber;
        }

        return Boolean.FALSE;
    }
}

