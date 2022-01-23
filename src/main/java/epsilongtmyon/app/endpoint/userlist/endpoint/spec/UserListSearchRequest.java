package epsilongtmyon.app.endpoint.userlist.endpoint.spec;

import epsilongtmyon.app.endpoint.userlist.bl.spec.UserListSearchParam;

/**
 * ユーザー検索のリクエスト
 */
public class UserListSearchRequest {

	/**
	 * ユーザー別名ID
	 */
	private String userAliasId;

	/**
	 * ユーザー名
	 */
	private String userName;

	/**
	 * メールアドレス
	 */
	private String mailAddress;

	public String getUserAliasId() {
		return userAliasId;
	}

	public void setUserAliasId(String userAliasId) {
		this.userAliasId = userAliasId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public UserListSearchParam toParam() {
		UserListSearchParam param = new UserListSearchParam();
		param.userAliasId = userAliasId;
		param.userName = userName;
		param.mailAddress = mailAddress;
		return param;
	}
}
