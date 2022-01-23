package epsilongtmyon.app.endpoint.bookmarklist.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epsilongtmyon.app.common.security.auth.AppUserProvider;
import epsilongtmyon.app.endpoint.bookmarklist.bl.BookmarkListService;
import epsilongtmyon.app.endpoint.bookmarklist.bl.spec.BookmarkListSearchParam;
import epsilongtmyon.app.endpoint.bookmarklist.bl.spec.BookmarkListSearchResult;
import epsilongtmyon.app.endpoint.bookmarklist.endpoint.spec.BookmarkListSearchRequest;
import epsilongtmyon.app.endpoint.bookmarklist.endpoint.spec.BookmarkListSearchResponse;

@RestController
@RequestMapping("api/bookmark-list")
public class BookmarkListController {

	private final BookmarkListService service;

	private final AppUserProvider appUserProvider;

	public BookmarkListController(BookmarkListService service, AppUserProvider appUserProvider) {
		this.service = service;
		this.appUserProvider = appUserProvider;
	}

	@GetMapping("search")
	public BookmarkListSearchResponse search(BookmarkListSearchRequest request) {

		final BookmarkListSearchParam param = request.toParam();
		final BookmarkListSearchResult result = service.search(param, appUserProvider.getDirectly());
		final BookmarkListSearchResponse response = BookmarkListSearchResponse.fromResult(result);

		return response;
	}

}
