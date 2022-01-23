package epsilongtmyon.app.endpoint.userlist.endpoint.spec;

import java.util.List;
import java.util.stream.Collectors;

import epsilongtmyon.app.endpoint.userlist.bl.spec.UserListSearchResult;
import epsilongtmyon.app.endpoint.userlist.endpoint.spec.dto.UserDtoResponse;

public class UserListSearchResponse {

	private List<UserDtoResponse> userList;

	public List<UserDtoResponse> getUserList() {
		return userList;
	}

	public void setUserList(List<UserDtoResponse> userList) {
		this.userList = userList;
	}

	public static UserListSearchResponse fromResult(UserListSearchResult result) {
		final UserListSearchResponse r = new UserListSearchResponse();
		r.userList = result.userList.stream().map(UserDtoResponse::fromDto).collect(Collectors.toList());

		return r;
	}
}
