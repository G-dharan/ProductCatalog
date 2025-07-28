package com.ecom.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecom.dtos.ErrorDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorDto> productNotFoundException(Exception e) {
		 ErrorDto errorDto = new ErrorDto();
		 errorDto.setStatus("Failure");
		 errorDto.setMessage(e.getMessage());
		 return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoProductProvidedException.class)
	public ResponseEntity<ErrorDto> NoProductProvidedException(Exception e) {
		 ErrorDto errorDto = new ErrorDto();
		 errorDto.setStatus("Failure");
		 errorDto.setMessage(e.getMessage());
		 return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProductNotCreatedException.class)
	public ResponseEntity<ErrorDto> ProductNotCreatedException(Exception e) {
		 ErrorDto errorDto = new ErrorDto();
		 errorDto.setStatus("Failure");
		 errorDto.setMessage(e.getMessage());
		 return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
