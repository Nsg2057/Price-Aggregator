package edu.nyu.nsg2057.webscraper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<Object> validationException(Exception exception) {
        return new ResponseEntity<>("Invalid request " + exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity<Object> exception(Exception exception) {
//        return new ResponseEntity<>(exception."Something went wrong in Backend " + exception.getLocalizedMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
//    }

}