package epsilongtmyon.app.endpoint.init.bl.spec;

import java.util.List;
import java.util.Optional;

public class InitResult {

	public Optional<String> xToken;

	public Optional<String> xHeaderName;

	public boolean authenticated;

	public List<String> authorities;
}
