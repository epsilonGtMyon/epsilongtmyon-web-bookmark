package epsilongtmyon.app.common.csrf;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;

public interface CsrfTokenProvider {

	Optional<CsrfToken> getToken();

	@Component
	public static class HttpServletRequestCsrfTokenProvider implements CsrfTokenProvider {

		private final HttpServletRequest request;

		public HttpServletRequestCsrfTokenProvider(HttpServletRequest request) {
			this.request = request;
		}

		@Override
		public Optional<CsrfToken> getToken() {
			CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
			return Optional.ofNullable(token);
		}
	}
}
