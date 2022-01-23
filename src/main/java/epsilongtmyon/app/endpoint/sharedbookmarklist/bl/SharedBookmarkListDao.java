package epsilongtmyon.app.endpoint.sharedbookmarklist.bl;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import epsilongtmyon.app.endpoint.sharedbookmarklist.bl.spec.dto.SharedBookmarkListDto;

@Dao
@ConfigAutowireable
public interface SharedBookmarkListDao {


	@Select
	List<SharedBookmarkListDto> selectSharedBookmarks();
}
