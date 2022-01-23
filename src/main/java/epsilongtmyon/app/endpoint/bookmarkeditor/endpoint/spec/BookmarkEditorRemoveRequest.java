package epsilongtmyon.app.endpoint.bookmarkeditor.endpoint.spec;

import epsilongtmyon.app.common.util.StringUtil;
import epsilongtmyon.app.common.validation.annotation.HalfNumber;
import epsilongtmyon.app.common.validation.annotation.MaxLength;
import epsilongtmyon.app.common.validation.annotation.Required;
import epsilongtmyon.app.endpoint.bookmarkeditor.bl.spec.BookmarkEditorRemoveParam;

public class BookmarkEditorRemoveRequest {

	/** ブックマークID */
	@Required
	@HalfNumber
	@MaxLength(maxLength = 10)
	private String bookmarkId;

	public String getBookmarkId() {
		return bookmarkId;
	}

	public void setBookmarkId(String bookmarkId) {
		this.bookmarkId = bookmarkId;
	}

	public BookmarkEditorRemoveParam toParam() {
		final BookmarkEditorRemoveParam p = new BookmarkEditorRemoveParam();
		p.setBookmarkId(StringUtil.toLong(bookmarkId));
		return p;
	}
}
