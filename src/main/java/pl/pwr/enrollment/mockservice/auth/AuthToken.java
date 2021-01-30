package pl.pwr.enrollment.mockservice.auth;

public class AuthToken {

	private final String value;

	public AuthToken(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
