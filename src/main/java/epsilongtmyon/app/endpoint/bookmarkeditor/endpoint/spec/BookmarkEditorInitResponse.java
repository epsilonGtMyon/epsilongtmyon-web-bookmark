package epsilongtmyon.app.endpoint.bookmarkeditor.endpoint.spec;

import epsilongtmyon.app.endpoint.bookmarkeditor.bl.spec.BookmarkEditorInitResult;

public class BookmarkEditorInitResponse {

	/** ブックマークID */
	private Long bookmarkId;

	/** タイトル */
	private String title;

	/** URL */
	private String url;

	/** 説明 */
	private String description;

	/** 共有フラグ */
	private boolean sharedFlag;

	public Long getBookmarkId() {
		return bookmarkId;
	}

	public void setBookmarkId(Long bookmarkId) {
		this.bookmarkId = bookmarkId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isSharedFlag() {
		return sharedFlag;
	}

	public void setSharedFlag(boolean sharedFlag) {
		this.sharedFlag = sharedFlag;
	}

	public static BookmarkEditorInitResponse fromResult(BookmarkEditorInitResult result) {
		final BookmarkEditorInitResponse r = new BookmarkEditorInitResponse();
		r.setBookmarkId(result.getBookmarkId());
		r.setTitle(result.getTitle());
		r.setUrl(result.getUrl());
		r.setDescription(result.getDescription());
		r.setSharedFlag(result.isSharedFlag());

		return r;
	}

}
