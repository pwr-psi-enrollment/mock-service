package pl.pwr.enrollment.mockservice.mockdata;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.pwr.enrollment.mockservice.error.MockDataNotFoundException;

@ControllerAdvice
public class MockRestControllerAdvice {

	@ExceptionHandler(MockDataNotFoundException.class)
	public ResponseEntity<?> handleMockFileNotFoundException() {
		return ResponseEntity.notFound().build();
	}
}
