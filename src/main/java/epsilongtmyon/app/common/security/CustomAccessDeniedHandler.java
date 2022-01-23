package epsilongtmyon.app.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.MissingCsrfTokenException;

/**
 * SPA用のAccessDeniedHandler
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	protected static final Log logger = LogFactory.getLog(CustomAccessDeniedHandler.class);

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		if (response.isCommitted()) {
			logger.trace("Did not write to response since already committed");
			return;
		}

		if (accessDeniedException instanceof MissingCsrfTokenException) {
			// セッション切れた時にCsrfFilterで起きる

			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return;
		}

		//それ以外
		response.setStatus(HttpStatus.FORBIDDEN.value());

	}

}
