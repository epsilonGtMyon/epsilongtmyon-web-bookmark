package epsilongtmyon.app.endpoint.bookmarkeditor.endpoint;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epsilongtmyon.app.common.security.auth.AppUserDetails;
import epsilongtmyon.app.common.security.auth.AppUserProvider;
import epsilongtmyon.app.endpoint.bookmarkeditor.bl.BookmarkEditorService;
import epsilongtmyon.app.endpoint.bookmarkeditor.bl.spec.BookmarkEditorInitParam;
import epsilongtmyon.app.endpoint.bookmarkeditor.bl.spec.BookmarkEditorInitResult;
import epsilongtmyon.app.endpoint.bookmarkeditor.bl.spec.BookmarkEditorRegisterParam;
import epsilongtmyon.app.endpoint.bookmarkeditor.bl.spec.BookmarkEditorRemoveParam;
import epsilongtmyon.app.endpoint.bookmarkeditor.endpoint.spec.BookmarkEditorInitRequest;
import epsilongtmyon.app.endpoint.bookmarkeditor.endpoint.spec.BookmarkEditorInitResponse;
import epsilongtmyon.app.endpoint.bookmarkeditor.endpoint.spec.BookmarkEditorRegisterRequest;
import epsilongtmyon.app.endpoint.bookmarkeditor.endpoint.spec.BookmarkEditorRegisterResponse;
import epsilongtmyon.app.endpoint.bookmarkeditor.endpoint.spec.BookmarkEditorRemoveRequest;

@RestController
@RequestMapping("api/bookmark-editor")
public class BookmarkEditorController {

	private final BookmarkEditorService service;

	private final AppUserProvider appUserProvider;

	public BookmarkEditorController(BookmarkEditorService service, AppUserProvider appUserProvider) {
		this.service = service;
		this.appUserProvider = appUserProvider;
	}

	@GetMapping("init")
	public BookmarkEditorInitResponse init(@Validated BookmarkEditorInitRequest request) {

		final BookmarkEditorInitParam param = request.toParam();
		final BookmarkEditorInitResult result = service.init(param);

		final BookmarkEditorInitResponse response = BookmarkEditorInitResponse.fromResult(result);
		return response;

	}

	@PostMapping("register")
	public BookmarkEditorRegisterResponse register(
			@Validated @RequestBody BookmarkEditorRegisterRequest request) {
		final BookmarkEditorRegisterParam param = request.toParam();
		final AppUserDetails appUser = appUserProvider.getDirectly();

		final Long bookmarkId = service.register(param, appUser);
		final BookmarkEditorRegisterResponse response = new BookmarkEditorRegisterResponse();
		response.setBookmarkId(bookmarkId);

		return response;
	}

	@PostMapping("remove")
	public void remove(@Validated @RequestBody BookmarkEditorRemoveRequest request) {
		final BookmarkEditorRemoveParam param = request.toParam();
		final AppUserDetails appUser = appUserProvider.getDirectly();
		service.remove(param, appUser);
	}
}
