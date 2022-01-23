package epsilongtmyon.app.endpoint.init.endpoint.spec;

import java.util.List;
import java.util.Optional;

import epsilongtmyon.app.endpoint.init.bl.spec.InitResult;

/**
 * initのレスポンスクラス
 */
public class InitResponse {
	public Optional<String> xToken;

	public Optional<String> xHeaderName;

	public boolean authenticated;

	public List<String> authorities;

	public static InitResponse fromResult(InitResult result) {
		final InitResponse resp = new InitResponse();
		resp.xToken = result.xToken;
		resp.xHeaderName = result.xHeaderName;
		resp.authenticated = result.authenticated;
		resp.authorities = result.authorities;

		return resp;
	}
}
