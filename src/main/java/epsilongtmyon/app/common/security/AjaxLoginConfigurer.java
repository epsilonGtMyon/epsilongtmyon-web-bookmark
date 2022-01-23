package epsilongtmyon.app.common.security;

import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

//FormLoginConfigurer を参考

public final class AjaxLoginConfigurer<H extends HttpSecurityBuilder<H>> extends
		AbstractAuthenticationFilterConfigurer<H, AjaxLoginConfigurer<H>, CustomUsernamePasswordAuthenticationFilter> {

	public AjaxLoginConfigurer() {
		super(new CustomUsernamePasswordAuthenticationFilter(), null);
	}

	@Override
	protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
		return new AntPathRequestMatcher(loginProcessingUrl, "POST");
	}

}
