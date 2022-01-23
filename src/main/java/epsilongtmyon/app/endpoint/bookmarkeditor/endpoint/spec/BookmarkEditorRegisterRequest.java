package epsilongtmyon.app.endpoint.bookmarkeditor.endpoint.spec;

import java.util.Objects;

import epsilongtmyon.app.common.util.StringUtil;
import epsilongtmyon.app.common.validation.annotation.HalfNumber;
import epsilongtmyon.app.common.validation.annotation.MaxLength;
import epsilongtmyon.app.common.validation.annotation.Required;
import epsilongtmyon.app.endpoint.bookmarkeditor.bl.spec.BookmarkEditorRegisterParam;

public class BookmarkEditorRegisterRequest {

	@HalfNumber
	@MaxLength(maxLength = 10)
	private String bookmarkId;

	@Required
	@MaxLength(maxLength = 30)
	private String title;

	@Required
	@MaxLength(maxLength = 200)
	private String url;

	@MaxLength(maxLength = 1000)
	private String description;

	@Required
	@MaxLength(maxLength = 1)
	private String sharedFlag;

	public String getBookmarkId() {
		return bookmarkId;
	}

	public void setBookmarkId(String bookmarkId) {
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

	public String getSharedFlag() {
		return sharedFlag;
	}

	public void setSharedFlag(String sharedFlag) {
		this.sharedFlag = sharedFlag;
	}

	public BookmarkEditorRegisterParam toParam() {
		final BookmarkEditorRegisterParam p = new BookmarkEditorRegisterParam();
		p.setBookmarkId(StringUtil.toLong(bookmarkId));
		p.setTitle(title);
		p.setUrl(url);
		p.setDescription(description);
		p.setSharedFlag(Objects.equals(sharedFlag, "1"));

		return p;
	}
}
