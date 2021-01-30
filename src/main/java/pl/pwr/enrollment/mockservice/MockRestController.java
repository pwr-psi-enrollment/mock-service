package pl.pwr.enrollment.mockservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{serviceName}")
public class MockRestController {

	private final MockService mockService;

	public MockRestController(MockService mockService) {
		this.mockService = mockService;
	}

	@GetMapping("/{resource}/{id}")
	public ResponseEntity<String> getDetails(@PathVariable String serviceName, @PathVariable String resource, @PathVariable String id) {
		String json = mockService.findOne(serviceName, resource, id);
		return ResponseEntity.ok(json);
	}

//	@GetMapping("/{resource}")
//	public ResponseEntity<List<JsonObject>> findAll(@PathVariable String serviceName, @PathVariable String resource) {
//		List<JsonObject> json = mockService.findAll(serviceName, resource);
//		return ResponseEntity.ok(json);
//	}

}
