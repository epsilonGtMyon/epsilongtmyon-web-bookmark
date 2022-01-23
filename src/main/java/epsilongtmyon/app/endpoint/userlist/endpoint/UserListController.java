package epsilongtmyon.app.endpoint.userlist.endpoint;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epsilongtmyon.app.endpoint.userlist.bl.UserListService;
import epsilongtmyon.app.endpoint.userlist.bl.spec.UserListSearchParam;
import epsilongtmyon.app.endpoint.userlist.bl.spec.UserListSearchResult;
import epsilongtmyon.app.endpoint.userlist.endpoint.spec.UserListSearchRequest;
import epsilongtmyon.app.endpoint.userlist.endpoint.spec.UserListSearchResponse;

@RestController
@RequestMapping("api/userlist")
public class UserListController {

	private final UserListService userListService;

	public UserListController(UserListService userListService) {
		this.userListService = userListService;
	}

	@GetMapping("search")
	public UserListSearchResponse search(@Validated UserListSearchRequest request) {
		final UserListSearchParam param = request.toParam();
		final UserListSearchResult result = userListService.search(param);

		final UserListSearchResponse response = UserListSearchResponse.fromResult(result);
		return response;
	}
}
