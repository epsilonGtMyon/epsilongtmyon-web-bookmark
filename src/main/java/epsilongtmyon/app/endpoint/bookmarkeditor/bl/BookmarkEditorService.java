package epsilongtmyon.app.endpoint.bookmarkeditor.bl;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import epsilongtmyon.app.common.appmessage.AppMessagesException;
import epsilongtmyon.app.common.db.dao.BookmarkDao;
import epsilongtmyon.app.common.db.entity.Bookmark;
import epsilongtmyon.app.common.security.auth.AppUserDetails;
import epsilongtmyon.app.endpoint.bookmarkeditor.bl.spec.BookmarkEditorInitParam;
import epsilongtmyon.app.endpoint.bookmarkeditor.bl.spec.BookmarkEditorInitResult;
import epsilongtmyon.app.endpoint.bookmarkeditor.bl.spec.BookmarkEditorRegisterParam;
import epsilongtmyon.app.endpoint.bookmarkeditor.bl.spec.BookmarkEditorRemoveParam;

@Service
@Transactional
public class BookmarkEditorService {

	private final BookmarkDao bookmarkDao;

	public BookmarkEditorService(BookmarkDao bookmarkDao) {
		this.bookmarkDao = bookmarkDao;
	}

	public BookmarkEditorInitResult init(BookmarkEditorInitParam param) {

		final BookmarkEditorInitResult r = new BookmarkEditorInitResult();
		final Bookmark bookmark = bookmarkDao.selectById(param.getBookmarkId());
		if (bookmark == null) {
			throw AppMessagesException.fromStringMessage("ブックマークが存在しません。");
		}

		r.setBookmarkId(bookmark.bookmarkId);
		r.setTitle(bookmark.title);
		r.setUrl(bookmark.url);
		r.setDescription(bookmark.description);
		r.setSharedFlag(bookmark.sharedFlag);
		return r;

	}

	public Long register(BookmarkEditorRegisterParam param, AppUserDetails appUser) {
		final boolean isNew = param.getBookmarkId() == null;

		Bookmark bookmark = bookmarkDao.selectById(param.getBookmarkId());
		if (isNew) {
			bookmark = new Bookmark();
			bookmark.userId = appUser.userId;
			bookmark.userListOrder = bookmarkDao.getMaxListOrderByUserId(appUser.userId) + 1;
			bookmark.createdDate = LocalDateTime.now();
		} else {
			if (bookmark == null) {
				throw AppMessagesException.fromStringMessage("ブックマークが存在しません。");
			}

			if (!Objects.equals(appUser.userId, bookmark.userId)) {
				throw AppMessagesException.fromStringMessage("他人のレコードは編集できません。");
			}
		}

		bookmark.title = param.getTitle();
		bookmark.url = param.getUrl();
		bookmark.description = param.getDescription();
		bookmark.sharedFlag = param.isSharedFlag();

		if (isNew) {
			bookmarkDao.insert(bookmark);
		} else {
			bookmarkDao.update(bookmark);
		}

		return bookmark.bookmarkId;
	}

	public void remove(BookmarkEditorRemoveParam param, AppUserDetails appUser) {
		Bookmark bookmark = bookmarkDao.selectById(param.getBookmarkId());

		if (bookmark == null) {
			//削除済みなので何もしない
			return;
		}

		if (!Objects.equals(appUser.userId, bookmark.userId)) {
			throw AppMessagesException.fromStringMessage("他人のレコードは編集できません。");
		}

		bookmarkDao.delete(bookmark);
	}
}
