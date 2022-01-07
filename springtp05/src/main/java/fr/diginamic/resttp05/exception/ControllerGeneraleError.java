package fr.diginamic.resttp05.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerGeneraleError {

	public ControllerGeneraleError() {
		// TODO Auto-generated constructor stub
	}
	@ExceptionHandler(value = {ClientNotFoundException.class})
	public ResponseEntity<String> onErrorClient(ClientNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ClientError : " + ex.getMessage());
	}
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<String> onError(Exception ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Other Error : " + ex.getMessage());
	}

}
