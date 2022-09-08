package com.digitalbooks.reader.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.digitalbooks.reader.exception.ReaderNotFoundException;
import com.digitalbooks.reader.model.ErrorResponse;
@ControllerAdvice
public class CommonExceptionHandler {
	@ExceptionHandler(ReaderNotFoundException.class) 
	public ResponseEntity<?> handleMovieNotFoundException(ReaderNotFoundException e) {
		ErrorResponse error = new ErrorResponse(500, e.getClass().toString(), e.getMessage(), null, LocalDateTime.now());
//		return error;
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
}
