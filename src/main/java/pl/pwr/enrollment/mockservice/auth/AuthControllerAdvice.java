package pl.pwr.enrollment.mockservice.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthControllerAdvice {

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<?> handleAuthenticationException() {
		return ResponseEntity.status(401).build();
	}

}
