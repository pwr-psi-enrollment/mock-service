package pl.pwr.enrollment.mockservice.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class User {

	private final Long id;
	private final String username;
	private final String password;
	private final List<String> authorities;

	@JsonCreator
	public User(
			@JsonProperty("id") Long id,
			@JsonProperty("username") String username,
			@JsonProperty("password") String password,
			@JsonProperty("authorities") List<String> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public List<String> getAuthorities() {
		return authorities;
	}
}
