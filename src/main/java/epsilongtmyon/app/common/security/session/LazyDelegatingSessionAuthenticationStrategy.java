package epsilongtmyon.app.common.security.session;

import java.util.function.Supplier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

//こっちのやり方もある
public class LazyDelegatingSessionAuthenticationStrategy implements SessionAuthenticationStrategy {

	private final Supplier<SessionAuthenticationStrategy> delegate;

	public LazyDelegatingSessionAuthenticationStrategy(Supplier<SessionAuthenticationStrategy> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void onAuthentication(Authentication authentication, HttpServletRequest request,
			HttpServletResponse response) throws SessionAuthenticationException {
		delegate.get().onAuthentication(authentication, request, response);
	}

}
