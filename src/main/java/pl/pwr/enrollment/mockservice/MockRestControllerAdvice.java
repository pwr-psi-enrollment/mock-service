package pl.pwr.enrollment.mockservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.pwr.enrollment.mockservice.error.MockFileNotFoundException;

@ControllerAdvice
public class MockRestControllerAdvice {

	@ExceptionHandler(MockFileNotFoundException.class)
	public ResponseEntity<?> handleMockFileNotFoundException() {
		return ResponseEntity.notFound().build();
	}
}
