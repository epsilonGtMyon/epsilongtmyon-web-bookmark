package epsilongtmyon.app.endpoint.sharedbookmarklist.bl.spec;

import java.util.List;

import epsilongtmyon.app.endpoint.sharedbookmarklist.bl.spec.dto.SharedBookmarkListDto;

public class SharedBookmarkListSearchResult {

	private List<SharedBookmarkListDto> sharedBookmarks;

	public List<SharedBookmarkListDto> getSharedBookmarks() {
		return sharedBookmarks;
	}

	public void setSharedBookmarks(List<SharedBookmarkListDto> sharedBookmarks) {
		this.sharedBookmarks = sharedBookmarks;
	}

}
