package epsilongtmyon.app.common.validation;

import java.util.Collection;
import java.util.regex.Pattern;

public final class ValidationUtil {

	private static final Pattern alphaNumberPattern = Pattern.compile("^[a-zA-Z0-9]+$");

	private ValidationUtil() {
		throw new UnsupportedOperationException();
	}

	private static boolean isEmptyString(String text) {
		return text == null || text.isEmpty();
	}

	/**
	 * 必須チェック
	 * @param value
	 * @return
	 */
	public static boolean required(Object value) {
		if (value == null) {
			return false;
		}
		if (value instanceof String) {
			return !((String) value).isBlank();
		}

		if (value instanceof Collection<?>) {
			return !((Collection<?>) value).isEmpty();
		}

		return true;
	}

	/**
	 * 最大桁数チェック
	 * @param value
	 * @param maxLength
	 * @return
	 */
	public static boolean maxLength(String value, int maxLength) {
		System.out.println("maxLength:" + value);
		if (isEmptyString(value)) {
			return true;
		}
		return value.length() <= maxLength;
	}

	/**
	 * 半角数値チェック
	 * @param value
	 * @return
	 */
	public static boolean halfNumber(String value) {
		System.out.println("halfNumber:" + value);
		if (isEmptyString(value)) {
			return true;
		}

		for (char c : value.toCharArray()) {
			if ('0' <= c && c <= '9') {
				continue;
			}
			return false;
		}
		return true;
	}

	/**
	 * 半角英数チェック
	 * @param value
	 * @return
	 */
	public static boolean halfAlphaNumber(String value) {
		System.out.println("halfAlphaNumber:" + value);
		if (isEmptyString(value)) {
			return true;
		}

		return alphaNumberPattern.matcher(value).matches();
	}

	/**
	 * メールアドレス形式(RFC準拠じゃなくてフィーリング)
	 * @param value
	 * @return
	 */
	public static boolean simpleMailAddress(String value) {
		System.out.println("simpleMailAddress:" + value);
		if (isEmptyString(value)) {
			return true;
		}
		String[] split = value.split("@");
		if (split.length != 2) {
			//@が一つであること
			return false;
		}
		//RFCムシでチェック

		String part1 = split[0];
		String part2 = split[1];
		if (!part1.matches("^[a-zA-Z0-9\\.\\-_]+$")) {
			return false;
		}
		if (!part2.matches("^[a-zA-Z0-9\\.\\-_]+$")) {
			return false;
		}

		if (part2.indexOf('.') < 0) {
			//後半に.がない
			return false;
		}
		return true;
	}
}
