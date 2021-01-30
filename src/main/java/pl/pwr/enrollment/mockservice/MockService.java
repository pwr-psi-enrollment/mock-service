package pl.pwr.enrollment.mockservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.pwr.enrollment.mockservice.error.MockFileNotFoundException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class MockService {

	private static final Logger log = LoggerFactory.getLogger(MockService.class);

	private final String dataBasePath;

	public MockService(@Value("${mock.data.path:data}") String dataBasePath) {
		this.dataBasePath = dataBasePath;
	}

	public String findOne(String serviceName, String resource, String id) {
		Path resourcePath = Path.of(dataBasePath, serviceName, resource + "_" + id + ".json");
		log.info("Requesting resource: {}", resourcePath);
		return readFileAsString(resourcePath);
	}

//	public List<JsonObject> findAll(String serviceName, String resource) {
//		try {
//			return Files.walk(Path.of(dataBasePath, serviceName))
//					.filter(path -> {
//						log.info("Walking file: {}", path.getFileName());
//						return path.getFileName().toString().startsWith(resource + "_");
//					})
//					.map(this::readFileAsString)
//					.collect(toList());
//		} catch (IOException e) {
//			throw new IllegalStateException("Failed to walk directory tree", e);
//		}
//	}

	public String readFileAsString(Path path) {
		if (!path.toFile().exists()) {
			throw new MockFileNotFoundException();
		}

		try {
			byte[] bytes = Files.readAllBytes(path);
			return new String(bytes, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new IllegalStateException("Failed to read file");
		}
	}
}
