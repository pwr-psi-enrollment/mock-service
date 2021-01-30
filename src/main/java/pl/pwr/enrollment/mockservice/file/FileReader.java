package pl.pwr.enrollment.mockservice.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.pwr.enrollment.mockservice.auth.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Component
public class FileReader {

	private static final Logger log = LoggerFactory.getLogger(FileReader.class);

	private final String dataBasePath;
	private final ObjectMapper objectMapper;

	public FileReader(@Value("${mock.data.path:data}") String dataBasePath, ObjectMapper objectMapper) {
		this.dataBasePath = dataBasePath;
		this.objectMapper = objectMapper;
	}

	public Optional<String> readMockData(String serviceName, String resource, String id) {
		Path resourcePath = Path.of(dataBasePath, serviceName, resource + "_" + id + ".json");
		log.info("Requesting resource: {}", resourcePath);
		return readFileAsString(resourcePath);
	}

	public Optional<User> readUser(String username) {
		Path path = Path.of(dataBasePath, "_users", username + ".json");
		log.info("Reading user data: {}", username);
		return readFileAsJson(path, User.class);
	}

	private Optional<String> readFileAsString(Path path) {
		if (!path.toFile().exists()) {
			return Optional.empty();
		}

		try {
			byte[] bytes = Files.readAllBytes(path);
			return Optional.of(
					new String(bytes, StandardCharsets.UTF_8)
			);
		} catch (IOException e) {
			throw new IllegalStateException("Failed to read file");
		}
	}

	private <T> Optional<T> readFileAsJson(Path path, Class<T> clazz) {
		return readFileAsString(path)
				.map(json -> {
					try {
						return objectMapper.readValue(json, clazz);
					} catch (JsonProcessingException e) {
						throw new IllegalStateException(e);
					}
				});
	}
}
