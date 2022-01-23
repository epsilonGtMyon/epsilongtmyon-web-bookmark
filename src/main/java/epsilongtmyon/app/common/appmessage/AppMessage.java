package epsilongtmyon.app.common.appmessage;

/**
 * メッセージ
 */
public class AppMessage {

	/**
	 * メッセージ対象プロパティ
	 */
	private String property;

	/**
	 * メッセージ(本文)
	 */
	private String message;

	public AppMessage() {
		this(null, null);
	}

	public AppMessage(String property, String message) {
		this.property = property;
		this.message = message;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
