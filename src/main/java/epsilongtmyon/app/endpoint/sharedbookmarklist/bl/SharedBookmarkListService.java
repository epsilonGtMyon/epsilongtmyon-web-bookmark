package epsilongtmyon.app.endpoint.sharedbookmarklist.bl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import epsilongtmyon.app.endpoint.sharedbookmarklist.bl.spec.SharedBookmarkListSearchResult;
import epsilongtmyon.app.endpoint.sharedbookmarklist.bl.spec.dto.SharedBookmarkListDto;

@Service
@Transactional
public class SharedBookmarkListService {

	private final SharedBookmarkListDao sharedBookmarkListDao;

	public SharedBookmarkListService(SharedBookmarkListDao sharedBookmarkListDao) {
		this.sharedBookmarkListDao = sharedBookmarkListDao;
	}

	public SharedBookmarkListSearchResult search() {
		SharedBookmarkListSearchResult r = new SharedBookmarkListSearchResult();

		List<SharedBookmarkListDto> sharedBookmarkDtos = sharedBookmarkListDao.selectSharedBookmarks();

		r.setSharedBookmarks(sharedBookmarkDtos);

		return r;
	}
}
