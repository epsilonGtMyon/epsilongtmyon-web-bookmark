package epsilongtmyon.app.common.security.auth;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

//@AuthenticationPrincipal で受け取るのとどっちがええのやら？
public interface AppUserProvider {

	Optional<AppUserDetails> get();

	default AppUserDetails getDirectly() {
		return get().get();
	}

	@Component
	public static class AppUserProviderImpl implements AppUserProvider {

		@Override
		public Optional<AppUserDetails> get() {
			SecurityContext context = SecurityContextHolder.getContext();
			Authentication authentication = context.getAuthentication();
			Object principal = authentication.getPrincipal();
			if (principal instanceof AppUserDetails) {
				return Optional.of((AppUserDetails) principal);
			}
			return Optional.empty();
		}

	}
}
