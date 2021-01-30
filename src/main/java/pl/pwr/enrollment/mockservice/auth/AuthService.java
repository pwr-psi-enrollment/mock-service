package pl.pwr.enrollment.mockservice.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.pwr.enrollment.mockservice.file.FileReader;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {

	private static final String AUTHORITIES_CLAIM_NAME = "authorities";
	private static final String USER_ID_CLAIM_NAME = "userId";

	private final Algorithm cipherAlgorithm;
	private final long tokenExpirationTime;
	private final FileReader fileReader;

	public AuthService(
			@Value("${auth.secret:P26mwUeQ664l}") String secret,
			@Value("${auth.tokenExpirationTime:3600}") long tokenExpirationTime,
			FileReader fileReader) {
		this.cipherAlgorithm = Algorithm.HMAC512(secret.getBytes());
		this.tokenExpirationTime = tokenExpirationTime;
		this.fileReader = fileReader;
	}

	public AuthToken authenticate(LoginDto loginDto) {
		User user = authUser(loginDto.getUsername(), loginDto.getPassword());

		String token = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + (tokenExpirationTime * 1000)))
				.withArrayClaim(AUTHORITIES_CLAIM_NAME, user.getAuthorities().toArray(new String[0]))
				.withClaim(USER_ID_CLAIM_NAME, user.getId())
				.sign(cipherAlgorithm);

		return new AuthToken(token);
	}

	private User authUser(String username, String password) {
		Optional<User> user = fileReader.readUser(username);
		if (user.isEmpty()) {
			throw new AuthenticationException();
		}

		if (!user.get().getPassword().equals(password)) {
			throw new AuthenticationException();
		}

		return user.get();
	}

}
