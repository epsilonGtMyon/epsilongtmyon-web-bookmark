package epsilongtmyon.app.endpoint.sharedbookmarklist.endpoint.spec.dto;

import epsilongtmyon.app.endpoint.sharedbookmarklist.bl.spec.dto.SharedBookmarkListDto;

public class SharedBookmarkListDtoResponse {

	/** ブックマークID */
	private Long bookmarkId;

	/** タイトル */
	private String title;

	/** URL */
	private String url;

	/** 説明 */
	private String description;

	/** ユーザー名 */
	private String userName;

	/** ユーザー別ID */
	private String userAliasId;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAliasId() {
		return userAliasId;
	}

	public void setUserAliasId(String userAliasId) {
		this.userAliasId = userAliasId;
	}

	public static SharedBookmarkListDtoResponse fromResult(SharedBookmarkListDto result) {
		final SharedBookmarkListDtoResponse r = new SharedBookmarkListDtoResponse();
		r.bookmarkId = result.getBookmarkId();
		r.title = result.getTitle();
		r.url = result.getUrl();
		r.description = result.getDescription();
		r.userName = result.getUserName();
		r.userAliasId = result.getUserAliasId();
		return r;
	}
}
