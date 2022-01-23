package epsilongtmyon.app.endpoint.userlist.bl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import epsilongtmyon.app.endpoint.userlist.bl.spec.UserListSearchParam;
import epsilongtmyon.app.endpoint.userlist.bl.spec.UserListSearchResult;
import epsilongtmyon.app.endpoint.userlist.bl.spec.dto.UserDto;

@Service
@Transactional
public class UserListService {

	private final UserListDao userListDao;

	public UserListService(UserListDao userListDao) {
		super();
		this.userListDao = userListDao;
	}

	public UserListSearchResult search(UserListSearchParam param) {
		final UserListSearchResult result = new UserListSearchResult();

		List<UserDto> userList = userListDao.search(param);
		result.userList = userList;

		return result;
	}

}
