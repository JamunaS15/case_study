package com.digitalbooks.book.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.digitalbooks.book.exception.BookNotFoundException;
import com.digitalbooks.book.model.ErrorResponse;
@ControllerAdvice
public class CommonExceptionHandler {
	@ExceptionHandler(BookNotFoundException.class) 
	public ResponseEntity<?> handleMovieNotFoundException(BookNotFoundException e) {
		ErrorResponse error = new ErrorResponse(500, e.getClass().toString(), e.getMessage(), null, LocalDateTime.now());

		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
}
