package epsilongtmyon.app.endpoint.bookmarklist.bl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import epsilongtmyon.app.common.db.dao.BookmarkDao;
import epsilongtmyon.app.common.db.entity.Bookmark;
import epsilongtmyon.app.common.security.auth.AppUserDetails;
import epsilongtmyon.app.endpoint.bookmarklist.bl.spec.BookmarkListSearchParam;
import epsilongtmyon.app.endpoint.bookmarklist.bl.spec.BookmarkListSearchResult;
import epsilongtmyon.app.endpoint.bookmarklist.bl.spec.dto.BookmarkListDto;

@Service
@Transactional
public class BookmarkListService {

	private final BookmarkDao bookmarkDao;

	public BookmarkListService(BookmarkDao bookmarkDao) {
		this.bookmarkDao = bookmarkDao;
	}

	public BookmarkListSearchResult search(BookmarkListSearchParam param, AppUserDetails appUserDetails) {
		final BookmarkListSearchResult r = new BookmarkListSearchResult();

		List<Bookmark> bookmarks = bookmarkDao.selectByUserId(appUserDetails.userId);

		List<BookmarkListDto> bookmarkDtos = bookmarks.stream().map(x -> {
			BookmarkListDto p = new BookmarkListDto();
			p.setBookmarkId(x.bookmarkId);
			p.setUserListOrder(x.userListOrder);
			p.setTitle(x.title);
			p.setUrl(x.url);
			p.setDescription(x.description);
			p.setSharedFlag(x.sharedFlag);
			return p;
		}).collect(Collectors.toList());

		r.setBookmarks(bookmarkDtos);

		return r;
	}
}
