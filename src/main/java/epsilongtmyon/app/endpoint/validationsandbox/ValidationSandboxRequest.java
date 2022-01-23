package epsilongtmyon.app.endpoint.validationsandbox;

import javax.validation.constraints.NotEmpty;

public class ValidationSandboxRequest {

	@NotEmpty
	private String value01;
}
