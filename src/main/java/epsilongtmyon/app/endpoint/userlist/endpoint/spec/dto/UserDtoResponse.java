package epsilongtmyon.app.endpoint.userlist.endpoint.spec.dto;

import epsilongtmyon.app.endpoint.userlist.bl.spec.dto.UserDto;

public class UserDtoResponse {

	/**
	 * ユーザー別名ID
	 */
	public String userAliasId;

	/**
	 * ユーザー名
	 */
	public String userName;

	/**
	 * メールアドレス
	 */
	public String mailAddress;

	/**
	 * 管理者フラグ
	 */
	public boolean adminFlag;

	public static UserDtoResponse fromDto(UserDto u) {
		final UserDtoResponse r = new UserDtoResponse();

		r.userAliasId = u.userAliasId;
		r.userName = u.userName;
		r.mailAddress = u.mailAddress;
		r.adminFlag = u.adminFlag;

		return r;
	}
}
