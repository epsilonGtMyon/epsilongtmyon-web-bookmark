package epsilongtmyon.app.endpoint.bookmarklist.endpoint.spec.dto;

import epsilongtmyon.app.endpoint.bookmarklist.bl.spec.dto.BookmarkListDto;

public class BookmarkListDtoResponse {

	/** ブックマークID */
	private Long bookmarkId;

	/** 表示順 */
	private Integer userListOrder;

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

	public Integer getUserListOrder() {
		return userListOrder;
	}

	public void setUserListOrder(Integer userListOrder) {
		this.userListOrder = userListOrder;
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

	public static BookmarkListDtoResponse fromResult(BookmarkListDto result) {
		final BookmarkListDtoResponse r = new BookmarkListDtoResponse();
		r.setBookmarkId(result.getBookmarkId());
		r.setUserListOrder(result.getUserListOrder());
		r.setTitle(result.getTitle());
		r.setUrl(result.getUrl());
		r.setDescription(result.getDescription());
		r.setSharedFlag(result.isSharedFlag());
		return r;
	}

}
