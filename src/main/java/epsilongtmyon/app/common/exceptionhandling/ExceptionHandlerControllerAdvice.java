package epsilongtmyon.app.common.exceptionhandling;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import epsilongtmyon.app.common.appmessage.AppMessage;
import epsilongtmyon.app.common.appmessage.AppMessagesException;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler {
	private static final String X_APPLICATION_ERROR_HEADER = "X-Application-Error";

	//とりあえずの仮実装

	//GET のバリデーションエラー
	@Override
	protected ResponseEntity<Object> handleBindException(
			BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		//アプリケーションエラー
		headers.add(X_APPLICATION_ERROR_HEADER, "1");

		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();

		ExceptionResult<Void> result = new ExceptionResult<>();
		result.messages = fieldErrors.stream().map(fe -> new AppMessage(fe.getField(), fe.getDefaultMessage()))
				.collect(Collectors.toList());

		return handleExceptionInternal(ex, result, headers, status, request);
	}

	//POST のバリデーションエラー
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		//アプリケーションエラー
		headers.add(X_APPLICATION_ERROR_HEADER, "1");

		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();

		ExceptionResult<Void> result = new ExceptionResult<>();
		result.messages = fieldErrors.stream().map(fe -> new AppMessage(fe.getField(), fe.getDefaultMessage()))
				.collect(Collectors.toList());

		return handleExceptionInternal(ex, result, headers, status, request);
	}

	//----------------
	//

	@ExceptionHandler({
			AppMessagesException.class
	})
	public final ResponseEntity<Object> handleException(AppMessagesException ex, WebRequest request) throws Exception {
		HttpHeaders headers = new HttpHeaders();

		//アプリケーションエラー
		headers.add(X_APPLICATION_ERROR_HEADER, "1");

		HttpStatus status = HttpStatus.BAD_REQUEST;

		ExceptionResult<Void> result = new ExceptionResult<>();
		result.messages = ex.getAppMessages();

		return handleExceptionInternal(ex, result, headers, status, request);
	}

	static class ExceptionResult<T> implements Serializable {

		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

		public List<AppMessage> messages = Collections.emptyList();

		public T body;
	}
}
