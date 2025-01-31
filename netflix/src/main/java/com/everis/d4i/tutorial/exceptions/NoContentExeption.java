package com.everis.d4i.tutorial.exceptions;

import com.everis.d4i.tutorial.dto.ErrorDto;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class NoContentExeption extends  NetflixException{

    public NoContentExeption(final String message) {
        super(HttpStatus.NO_CONTENT.value(), message);
    }

    public NoContentExeption(final String message, final ErrorDto data) {
        super(HttpStatus.NO_CONTENT.value(), message, Arrays.asList(data));
    }

}
