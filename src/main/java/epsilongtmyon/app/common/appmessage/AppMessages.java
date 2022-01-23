package epsilongtmyon.app.common.appmessage;

import java.util.ArrayList;
import java.util.List;

public class AppMessages {

	private final List<AppMessage> appMessages;

	public AppMessages() {
		this.appMessages = new ArrayList<>();
	}

	public void addAppMessage(AppMessage appMessage) {
		appMessages.add(appMessage);
	}

	public void addMessage(String property, String message) {
		AppMessage a = new AppMessage(property, message);
		addAppMessage(a);
	}

	public void addMessage(String message) {
		addMessage("", message);
	}

	public boolean hasMessage() {
		return !appMessages.isEmpty();
	}

	public void throwIfHasMessage() {
		if (hasMessage()) {
			throw new AppMessagesException(appMessages);
		}
	}

	public List<AppMessage> getAppMessages() {
		return appMessages;
	}

}
