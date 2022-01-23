package epsilongtmyon.app.common.security.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AppUserDetailsService implements UserDetailsService {

	private final AuthDao authDao;

	public AppUserDetailsService(AuthDao authDao) {
		this.authDao = authDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final AppUserDetails user = authDao
				.findByUserAliasId(username)
				.orElseThrow(() -> new UsernameNotFoundException(username));
		return user;
	}

}
