package epsilongtmyon.app.endpoint.validationsandbox;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epsilongtmyon.app.common.appmessage.AppMessages;
import epsilongtmyon.app.common.appmessage.AppMessagesException;

@RestController
@RequestMapping("api/validationsandbox")
public class ValidationSandboxController {

	@GetMapping("get")
	public String get(@Validated ValidationSandboxRequest request) {
		return "x";
	}

	@PostMapping("post")
	public String post(@RequestBody @Validated ValidationSandboxRequest request) {
		return "x";
	}

	@GetMapping("appmessage")
	public String appMessage() {
		AppMessages appMessages = new AppMessages();

		appMessages.addMessage("ダメです。");
		appMessages.addMessage("are", "あれがダメです。");

		throw new AppMessagesException(appMessages);
	}
}
