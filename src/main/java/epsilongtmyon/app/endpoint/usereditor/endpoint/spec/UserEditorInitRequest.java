package epsilongtmyon.app.endpoint.usereditor.endpoint.spec;

import epsilongtmyon.app.common.validation.annotation.HalfAlphaNumber;
import epsilongtmyon.app.common.validation.annotation.MaxLength;

public class UserEditorInitRequest {

	/** ユーザー別名ID */
	@HalfAlphaNumber
	@MaxLength(maxLength = 10)
	private String userAliasId;

	public String getUserAliasId() {
		return userAliasId;
	}

	public void setUserAliasId(String userAliasId) {
		this.userAliasId = userAliasId;
	}

}
