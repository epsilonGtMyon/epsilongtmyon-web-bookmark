package epsilongtmyon.app.common.security.auth;

import java.util.Optional;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

@Dao
@ConfigAutowireable
public interface AuthDao {

	@Select
	Optional<AppUserDetails> findByUserAliasId(String userAliasId);
}
