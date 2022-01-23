package epsilongtmyon.app.endpoint.sharedbookmarklist.endpoint.spec;

import java.util.List;
import java.util.stream.Collectors;

import epsilongtmyon.app.endpoint.sharedbookmarklist.bl.spec.SharedBookmarkListSearchResult;
import epsilongtmyon.app.endpoint.sharedbookmarklist.endpoint.spec.dto.SharedBookmarkListDtoResponse;

public class SharedBookmarkListSearchResponse {

	private List<SharedBookmarkListDtoResponse> sharedBookmarks;

	public List<SharedBookmarkListDtoResponse> getSharedBookmarks() {
		return sharedBookmarks;
	}

	public void setSharedBookmarks(List<SharedBookmarkListDtoResponse> sharedBookmarks) {
		this.sharedBookmarks = sharedBookmarks;
	}

	public static SharedBookmarkListSearchResponse fromResult(SharedBookmarkListSearchResult result) {
		SharedBookmarkListSearchResponse r = new SharedBookmarkListSearchResponse();
		r.sharedBookmarks = result.getSharedBookmarks().stream().map(SharedBookmarkListDtoResponse::fromResult)
				.collect(Collectors.toList());
		return r;
	}
}
