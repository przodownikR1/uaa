package pl.java.scalatech.web;

import static org.springframework.security.core.authority.AuthorityUtils.authorityListToSet;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pl.java.scalatech.config.ProfileApp;

@RestController
@RequestMapping("/oauth2")
@Profile(ProfileApp.PROFILE)
@RequiredArgsConstructor
public class AuthHelpController {
	private static final String AUTHORITIES = "authorities";

	private static final String USER2 = "user";

	private final OAuth2RestOperations restTemplate;

	private final OAuth2ClientContext context;

	private final ClientDetailsService clientDetailsService;

	@GetMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

	@GetMapping("/clientToken")
	OAuth2AccessToken clientToken() {
		return restTemplate.getAccessToken();
	}

	@GetMapping("/user2")
	public Object user2(Principal user) {
		OAuth2Authentication authentication = (OAuth2Authentication) user;
		Authentication userAuthentication = authentication.getUserAuthentication();
		return userAuthentication.getPrincipal();
	}

	@GetMapping("/info")
	String info() {
		OAuth2AccessToken token = context.getAccessToken();
		String tokenValueBeforeDeletion = token.getValue();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return "Oauth Token from context : " + tokenValueBeforeDeletion;
	}

	@GetMapping("/client/{clientId}")
	ClientDetails client(@PathVariable String clientId) {
		ClientDetails cd = clientDetailsService.loadClientByClientId(clientId);
		return cd;
	}

	@GetMapping("/details")
	Object details(OAuth2Authentication authentication) {
		return authentication.getUserAuthentication();
	}

	@GetMapping("/show_token")
	String authCode() throws Exception {
		OAuth2Authentication auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
		OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
		String tokenValue = details.getTokenValue();
		return tokenValue;
	}

	@GetMapping(value = "userOauth", produces = "application/json")
	Map<String, Object> user(OAuth2Authentication user) {
		Map<String, Object> userDetails = new HashMap<>();
		userDetails.put(USER2, user.getUserAuthentication().getPrincipal());
		userDetails.put(AUTHORITIES, authorityListToSet(user.getUserAuthentication().getAuthorities()));
		return userDetails;
	}
}
