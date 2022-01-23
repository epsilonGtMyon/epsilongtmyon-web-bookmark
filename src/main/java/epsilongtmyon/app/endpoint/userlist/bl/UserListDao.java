package epsilongtmyon.app.endpoint.userlist.bl;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import epsilongtmyon.app.endpoint.userlist.bl.spec.UserListSearchParam;
import epsilongtmyon.app.endpoint.userlist.bl.spec.dto.UserDto;

@Dao
@ConfigAutowireable
public interface UserListDao {

	@Select
	List<UserDto> search(UserListSearchParam param);
}
