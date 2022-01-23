package epsilongtmyon.app.endpoint.usereditor.bl.spec;

public class UserEditorRegisterParam {

	/** ユーザーID */
	private Long userId;

	/** ユーザー別ID */
	private String userAliasId;

	/** ユーザー名 */
	private String userName;

	/** メールアドレス */
	private String mailAddress;

	/** 管理者フラグ */
	private boolean adminFlag;

	/** パスワード */
	private String password;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

	public boolean isAdminFlag() {
		return adminFlag;
	}

	public void setAdminFlag(boolean adminFlag) {
		this.adminFlag = adminFlag;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
