package epsilongtmyon.app.endpoint.usereditor.endpoint.spec;

import epsilongtmyon.app.common.validation.annotation.HalfNumber;
import epsilongtmyon.app.common.validation.annotation.Required;

public class UserEditorRemoveRequest {

	/** ユーザーID */
	@Required
	@HalfNumber
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
