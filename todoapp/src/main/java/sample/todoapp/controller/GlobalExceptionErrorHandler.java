package sample.todoapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.todoapp.exception.ErrorResponse;
import sample.todoapp.exception.GlobalException;

@ControllerAdvice
public class GlobalExceptionErrorHandler {

	/**
	 * @param req
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = { GlobalException.class })
	public ResponseEntity<ErrorResponse> handleGlobalException(
			GlobalException ex) {
		var response = new ErrorResponse(ex);
		return ResponseEntity.ok(response);
	}
}
