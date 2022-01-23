package epsilongtmyon.app.endpoint.sharedbookmarklist.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epsilongtmyon.app.endpoint.sharedbookmarklist.bl.SharedBookmarkListService;
import epsilongtmyon.app.endpoint.sharedbookmarklist.bl.spec.SharedBookmarkListSearchResult;
import epsilongtmyon.app.endpoint.sharedbookmarklist.endpoint.spec.SharedBookmarkListSearchResponse;

@RestController
@RequestMapping("api/shared-bookmark-list")
public class SharedBookmarkListController {

	private final SharedBookmarkListService service;

	public SharedBookmarkListController(SharedBookmarkListService service) {
		this.service = service;
	}

	@GetMapping("search")
	public SharedBookmarkListSearchResponse search() {
		final SharedBookmarkListSearchResult result = service.search();
		final SharedBookmarkListSearchResponse response = SharedBookmarkListSearchResponse.fromResult(result);
		return response;
	}

}
