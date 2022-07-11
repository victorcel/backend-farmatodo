package com.farmatodo.backend.controller;

import com.farmatodo.backend.dto.EpisodeDTO;
import com.farmatodo.backend.dto.NumbersDTO;
import com.farmatodo.backend.dto.ResponseDTO;
import com.farmatodo.backend.exception.ExceptionHandler;
import com.farmatodo.backend.useCase.Summation.SummationInterface;
import com.farmatodo.backend.useCase.getEpisode.GetEpisodeInterface;
import com.farmatodo.backend.useCase.happyNumber.HappyNumberInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiRestController {
    @Autowired
    private HappyNumberInterface happyNumber;

    @Autowired
    private SummationInterface summation;

    @Autowired
    private GetEpisodeInterface getEpisode;


    @RequestMapping(value = "/happynumber/{numbers}", method = RequestMethod.GET)
    public ResponseEntity<Object> calculateHappyNumbers(@PathVariable String numbers) throws Exception {
        try {
            NumbersDTO response = happyNumber.calculateHappyNumbers(numbers);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exc) {
            ExceptionHandler exceptionHandler = new ExceptionHandler();
            return exceptionHandler.toResponse(exc, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/summation/{number}", method = RequestMethod.GET)
    public ResponseEntity<Object> calculate(@PathVariable Long number) throws Exception {
        try {
            ResponseDTO response = summation.calculate(number);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exc) {
            ExceptionHandler exceptionHandler = new ExceptionHandler();
            return exceptionHandler.toResponse(exc, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/episode/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getEpisodeById(@PathVariable String id) throws Exception {
        try {
            EpisodeDTO response = getEpisode.show(id);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exc) {
            ExceptionHandler exceptionHandler = new ExceptionHandler();
            return exceptionHandler.toResponse(exc, HttpStatus.NOT_FOUND);
        }
    }
}
