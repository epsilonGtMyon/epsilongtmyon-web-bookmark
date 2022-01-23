package epsilongtmyon.app.endpoint.bookmarklist.bl.spec;

import java.util.List;

import epsilongtmyon.app.endpoint.bookmarklist.bl.spec.dto.BookmarkListDto;

public class BookmarkListSearchResult {

	private List<BookmarkListDto> bookmarks;

	public List<BookmarkListDto> getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(List<BookmarkListDto> bookmarks) {
		this.bookmarks = bookmarks;
	}

}
