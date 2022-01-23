package epsilongtmyon.app.common.util;

public class StringUtil {

	private StringUtil() {
		throw new UnsupportedOperationException();
	}

	public static boolean isEmpty(String value) {
		return value == null || value.isEmpty();
	}

	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}

	public static Long toLong(String value) {
		return isEmpty(value) ? null : Long.valueOf(value);
	}
}
