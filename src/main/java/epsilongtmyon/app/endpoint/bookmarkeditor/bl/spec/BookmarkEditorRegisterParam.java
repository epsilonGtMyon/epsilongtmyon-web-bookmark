package epsilongtmyon.app.endpoint.bookmarkeditor.bl.spec;

public class BookmarkEditorRegisterParam {

	private Long bookmarkId;

	private String title;

	private String url;

	private String description;

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

}
