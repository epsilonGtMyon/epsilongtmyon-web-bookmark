package epsilongtmyon.app.common.appmessage;

import java.util.List;

public class AppMessagesException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private final List<AppMessage> appMessages;

	public AppMessagesException(List<AppMessage> appMessages) {
		this.appMessages = appMessages;
	}

	public AppMessagesException(AppMessages appMessages) {
		this(appMessages.getAppMessages());
	}

	public List<AppMessage> getAppMessages() {
		return appMessages;
	}

	public static AppMessagesException fromStringMessage(String message) {
		AppMessages appMessages = new AppMessages();
		appMessages.addMessage(message);
		return new AppMessagesException(appMessages);
	}
}
