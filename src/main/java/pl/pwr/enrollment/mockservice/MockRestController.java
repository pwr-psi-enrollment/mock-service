package pl.pwr.enrollment.mockservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{serviceName}")
public class MockRestController {

	private static final Logger log = LoggerFactory.getLogger(MockRestController.class);

	private final MockService mockService;

	public MockRestController(MockService mockService) {
		this.mockService = mockService;
	}

	// /students-service/faculties
	@GetMapping("/{resource}")
	public ResponseEntity<String> findAll(@PathVariable String serviceName, @PathVariable String resource) {
		String json = mockService.findAll(serviceName, resource);
		return ResponseEntity.ok(json);
	}

	// /students-service/faculties/5
	@GetMapping("/{resource}/{id}")
	public ResponseEntity<String> getDetails(@PathVariable String serviceName, @PathVariable String resource, @PathVariable String id) {
		String json = mockService.findOne(serviceName, resource);
		return ResponseEntity.ok(json);
	}

}
