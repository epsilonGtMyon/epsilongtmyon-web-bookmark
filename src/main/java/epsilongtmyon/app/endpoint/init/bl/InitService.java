package epsilongtmyon.app.endpoint.init.bl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import epsilongtmyon.app.common.csrf.CsrfTokenProvider;
import epsilongtmyon.app.common.security.auth.AppUserDetails;
import epsilongtmyon.app.endpoint.init.bl.spec.InitResult;

@Service
@Transactional
public class InitService {

	private final CsrfTokenProvider csrfTokenProvider;

	public InitService(CsrfTokenProvider csrfTokenProvider) {
		this.csrfTokenProvider = csrfTokenProvider;
	}

	public InitResult init() {
		final InitResult result = new InitResult();

		final Optional<CsrfToken> csrfToken = csrfTokenProvider.getToken();
		result.xToken = csrfToken.map(CsrfToken::getToken);
		result.xHeaderName = csrfToken.map(CsrfToken::getHeaderName);
		result.authenticated = false;

		final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof AppUserDetails) {
			AppUserDetails appUser = (AppUserDetails) principal;
			result.authenticated = true;
			result.authorities = appUser.getAuthorities().stream().map(GrantedAuthority::getAuthority)
					.collect(Collectors.toList());
		}
		return result;
	}
}
