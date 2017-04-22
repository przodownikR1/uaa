package pl.java.scalatech.web;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/*import pl.java.scalatech.audit.db.Audit;
import pl.java.scalatech.audit.db.AuditRepo;
import pl.java.scalatech.audit.db.AuditType;
*/
@RestController
@RequestMapping("secure/user")
@RequiredArgsConstructor
public class UserInfoResource {

	private final UserDetailsService userDetailsService;

	@GetMapping("{userId}")
	public UserDetails getUserDetails(@PathVariable String userId) {
		return userDetailsService.loadUserByUsername(userId);
	}

	/*
	 * @RequestMapping("{userId}/login-history") public List<Audit>
	 * getLogins(@PathVariable String userId) { return
	 * auditRepo.findByUsernameAndAuditType(userId, AuditType.LOGGED_IN); }
	 */
}