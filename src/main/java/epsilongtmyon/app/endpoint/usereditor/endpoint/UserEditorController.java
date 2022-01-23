package epsilongtmyon.app.endpoint.usereditor.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epsilongtmyon.app.common.security.auth.AppUserDetails;
import epsilongtmyon.app.common.security.auth.AppUserProvider;
import epsilongtmyon.app.endpoint.usereditor.bl.UserEditorService;
import epsilongtmyon.app.endpoint.usereditor.bl.spec.UserEditorInitResult;
import epsilongtmyon.app.endpoint.usereditor.bl.spec.UserEditorRegisterParam;
import epsilongtmyon.app.endpoint.usereditor.endpoint.spec.UserEditorInitRequest;
import epsilongtmyon.app.endpoint.usereditor.endpoint.spec.UserEditorInitResponse;
import epsilongtmyon.app.endpoint.usereditor.endpoint.spec.UserEditorRegisterRequest;
import epsilongtmyon.app.endpoint.usereditor.endpoint.spec.UserEditorRegisterResponse;
import epsilongtmyon.app.endpoint.usereditor.endpoint.spec.UserEditorRemoveRequest;

//TODO 認可が必要(管理者だけ)

@RestController
@RequestMapping("api/usereditor")
public class UserEditorController {

	private final UserEditorService userEditorService;

	private final AppUserProvider appUserProvider;

	public UserEditorController(UserEditorService userEditorService, AppUserProvider appUserProvider) {
		this.userEditorService = userEditorService;
		this.appUserProvider = appUserProvider;
	}

	/**
	 * 初期処理
	 * @param request
	 * @return
	 */
	@GetMapping("init")
	public UserEditorInitResponse init(@Validated UserEditorInitRequest request) {

		final UserEditorInitResult result = userEditorService.init(request.getUserAliasId());
		final UserEditorInitResponse response = UserEditorInitResponse.fromResult(result);

		return response;
	}

	/**
	 * 登録処理
	 * @param request
	 * @return
	 */
	@PostMapping("register")
	public UserEditorRegisterResponse register(@Validated @RequestBody UserEditorRegisterRequest request) {
		final UserEditorRegisterResponse response = new UserEditorRegisterResponse();

		final UserEditorRegisterParam param = request.toParam();
		final Long register = userEditorService.register(param);

		response.setUserId(register);
		return response;
	}

	/**
	 * 削除処理
	 * @param request
	 * @return
	 */
	@PostMapping("remove")
	public ResponseEntity<?> remove(@Validated @RequestBody UserEditorRemoveRequest request) {
		Long userId = Long.valueOf(request.getUserId());
		AppUserDetails appUserDetails = appUserProvider.getDirectly();
		userEditorService.remove(userId, appUserDetails);
		return ResponseEntity.ok().build();
	}
}
