package pl.pwr.enrollment.mockservice.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/auth/login")
	public AuthToken login(@RequestBody LoginDto loginDto) {
		return authService.authenticate(loginDto);
	}

}
