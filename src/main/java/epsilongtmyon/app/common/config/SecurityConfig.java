package epsilongtmyon.app.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import epsilongtmyon.app.common.security.AjaxLoginConfigurer;
import epsilongtmyon.app.common.security.CustomAccessDeniedHandler;
import epsilongtmyon.app.common.security.CustomAuthenticationEntryPoint;
import epsilongtmyon.app.common.security.JsonBodyAuthenticationFailureHandler;
import epsilongtmyon.app.common.security.JsonBodyAuthenticationSuccessHandler;
import epsilongtmyon.app.common.security.auth.AppRole;
import epsilongtmyon.app.common.security.auth.AppUserDetailsService;
import epsilongtmyon.app.common.security.auth.AuthDao;

//https://spring.io/guides/gs/securing-web/
/**
 * Spring Security の設定
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests(authorize -> {
					authorize

							.antMatchers("/api/init/init").permitAll()
							.antMatchers(
									"/api/usereditor/**",
									"/api/userlist/**")
							.hasAnyRole(AppRole.ADMIN.getShortRoleName())
							//apiへのリクエストは認証必要
							.antMatchers("/api/**").authenticated()
							//その他すべてをpermitAllにするのはちょっと嫌だが
							//SPA用にそれ以外はフォールバック
							.anyRequest().permitAll()
							;


				})

				.logout(x -> {
					//ログアウトURL
					x.logoutUrl("/api/logout");
					//デフォルトだとリダイレクトするSimpleUrlLogoutSuccessHandlerが使われるので HTTPステータスコードを返すものに変更
					x.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
				})

				.apply(new AjaxLoginConfigurer<>())
				.loginProcessingUrl("/api/login")
				.successHandler(new JsonBodyAuthenticationSuccessHandler())
				.failureHandler(new JsonBodyAuthenticationFailureHandler())

				.and()

		;


		http.exceptionHandling(exceptionHandling -> {
			//認証きれたとき
			exceptionHandling.authenticationEntryPoint(new CustomAuthenticationEntryPoint());
			//認可エラー
			exceptionHandling.accessDeniedHandler(new CustomAccessDeniedHandler());
		});
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService(AuthDao authDao) {
		return new AppUserDetailsService(authDao);
	}
}
