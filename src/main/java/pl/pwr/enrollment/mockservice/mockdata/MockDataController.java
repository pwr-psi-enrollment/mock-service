package pl.pwr.enrollment.mockservice.mockdata;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/mock-service")
public class MockDataController {

	private static final String BEARER = "Bearer";

	private final MockDataService mockDataService;

	public MockDataController(MockDataService mockDataService) {
		this.mockDataService = mockDataService;
	}

	@GetMapping("/student-details")
	public ResponseEntity<String> getCurrentUserDetails(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
		Optional<String> token = fetchToken(authorization);

		if (token.isEmpty()) {
			return ResponseEntity.status(401).build();
		}

		return mockDataService.getStudentDetails(token.get())
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/courses")
	public ResponseEntity<String> getCourses(@RequestParam("registrationId") Long registrationId) {
		return mockDataService.getCourses(registrationId)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/semesters")
	public ResponseEntity<String> getSemesters(@RequestParam("registeredId") Long registeredId) {
		return mockDataService.getSemesters(registeredId)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	private Optional<String> fetchToken(String authorization) {
		if (!authorization.contains(BEARER)) {
			return Optional.empty();
		}

		String token = authorization.substring(BEARER.length()).trim();
		return Optional.of(token);
	}

}
