package epsilongtmyon.app.endpoint.usereditor.endpoint.spec;

import epsilongtmyon.app.common.util.StringUtil;
import epsilongtmyon.app.common.validation.annotation.HalfAlphaNumber;
import epsilongtmyon.app.common.validation.annotation.HalfNumber;
import epsilongtmyon.app.common.validation.annotation.MaxLength;
import epsilongtmyon.app.common.validation.annotation.Required;
import epsilongtmyon.app.common.validation.annotation.SimpleMailAddress;
import epsilongtmyon.app.endpoint.usereditor.bl.spec.UserEditorRegisterParam;

public class UserEditorRegisterRequest {

	/** ユーザーID */
	@HalfNumber
	private String userId;

	/** ユーザー別ID */
	@Required
	@HalfAlphaNumber
	@MaxLength(maxLength = 10)
	private String userAliasId;

	/** ユーザー名 */
	@Required
	@MaxLength(maxLength = 10)
	private String userName;

	/** メールアドレス */
	@Required
	@SimpleMailAddress
	@MaxLength(maxLength = 255)
	private String mailAddress;

	/** 管理者フラグ */
	private boolean adminFlag;

	/** パスワード */
	@MaxLength(maxLength = 255)
	private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
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

	public UserEditorRegisterParam toParam() {
		UserEditorRegisterParam param = new UserEditorRegisterParam();
		if (StringUtil.isNotEmpty(userId)) {
			param.setUserId(Long.valueOf(userId));
		}
		param.setUserAliasId(userAliasId);
		param.setUserName(userName);
		param.setMailAddress(mailAddress);
		param.setAdminFlag(adminFlag);
		param.setPassword(password);

		return param;
	}
}
