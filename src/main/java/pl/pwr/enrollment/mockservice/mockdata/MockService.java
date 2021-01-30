package pl.pwr.enrollment.mockservice.mockdata;

import org.springframework.stereotype.Service;
import pl.pwr.enrollment.mockservice.error.MockDataNotFoundException;
import pl.pwr.enrollment.mockservice.file.FileReader;

@Service
public class MockService {

	private final FileReader fileReader;

	public MockService(FileReader fileReader) {
		this.fileReader = fileReader;
	}

	public String findOne(String serviceName, String resource, String id) {
		return fileReader.readMockData(serviceName, resource, id)
				.orElseThrow(MockDataNotFoundException::new);
	}

}
