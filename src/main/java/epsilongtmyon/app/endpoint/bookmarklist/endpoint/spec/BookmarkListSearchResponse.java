package epsilongtmyon.app.endpoint.bookmarklist.endpoint.spec;

import java.util.List;
import java.util.stream.Collectors;

import epsilongtmyon.app.endpoint.bookmarklist.bl.spec.BookmarkListSearchResult;
import epsilongtmyon.app.endpoint.bookmarklist.endpoint.spec.dto.BookmarkListDtoResponse;

public class BookmarkListSearchResponse {

	private List<BookmarkListDtoResponse> bookmarks;

	public List<BookmarkListDtoResponse> getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(List<BookmarkListDtoResponse> bookmarks) {
		this.bookmarks = bookmarks;
	}

	public static BookmarkListSearchResponse fromResult(BookmarkListSearchResult result) {
		final BookmarkListSearchResponse r = new BookmarkListSearchResponse();
		r.setBookmarks(result.getBookmarks().stream().map(BookmarkListDtoResponse::fromResult)
				.collect(Collectors.toList()));
		return r;
	}
}
