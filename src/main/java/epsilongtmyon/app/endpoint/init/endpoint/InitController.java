package epsilongtmyon.app.endpoint.init.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epsilongtmyon.app.endpoint.init.bl.InitService;
import epsilongtmyon.app.endpoint.init.bl.spec.InitResult;
import epsilongtmyon.app.endpoint.init.endpoint.spec.InitResponse;

/**
 * 初期処理用のコントローラー
 */
@RestController
@RequestMapping("api/init")
public class InitController {

	private final InitService initService;

	public InitController(InitService initService) {
		this.initService = initService;
	}

	@GetMapping("init")
	public InitResponse init() {
		final InitResult result = initService.init();
		final InitResponse resp = InitResponse.fromResult(result);


		return resp;
	}
}
