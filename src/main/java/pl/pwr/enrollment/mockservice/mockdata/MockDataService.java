package pl.pwr.enrollment.mockservice.mockdata;

import org.springframework.stereotype.Service;
import pl.pwr.enrollment.mockservice.auth.AuthService;
import pl.pwr.enrollment.mockservice.file.FileReader;

import java.util.Optional;

@Service
public class MockDataService {

	private final AuthService authService;
	private final FileReader fileReader;

	public MockDataService(AuthService authService, FileReader fileReader) {
		this.authService = authService;
		this.fileReader = fileReader;
	}

	public Optional<String> getCourses(Long semesterId, Long registeredId) {
		return fileReader.readCoursesData(semesterId, registeredId);
	}

	public Optional<String> getSemesters(Long registeredId) {
		return fileReader.readSemestersData(registeredId);
	}

	public Optional<String> getStudentDetails(String token) {
		Long userId = authService.fetchUserId(token);
		return fileReader.readStudentsData(userId);
	}
}
