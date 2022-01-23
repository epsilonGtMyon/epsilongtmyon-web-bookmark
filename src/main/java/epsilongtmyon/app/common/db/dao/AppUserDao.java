package epsilongtmyon.app.common.db.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.criteria.Entityql;
import org.seasar.doma.jdbc.criteria.NativeSql;
import org.seasar.doma.jdbc.criteria.expression.Expressions;

import epsilongtmyon.app.common.db.entity.AppUser;
import epsilongtmyon.app.common.db.entity.AppUser_;

/**
 */
@Dao
@ConfigAutowireable
public interface AppUserDao {

	/**
	 * @param userId
	 * @return the AppUser entity
	 */
	@Select
	AppUser selectById(Long userId);

	/**
	 * ユーザー別名IDを条件に検索します。
	 * @param userAliasId
	 * @return
	 */
	default AppUser selectByUserAliasId(String userAliasId) {
		Config config = Config.get(this);
		AppUser_ u = new AppUser_();

		Entityql entityql = new Entityql(config);
		return entityql.from(u)

				.where(c -> c.eq(u.userAliasId, userAliasId))

				.fetchOne();

	}

	default boolean existsByUserAliasId(Long userId, String userAliasId) {
		AppUser_ u = new AppUser_();
		Config config = Config.get(this);

		NativeSql nativeSql = new NativeSql(config);
		return nativeSql.from(u)

				.where(c -> {
					c.eq(u.userAliasId, userAliasId);
					if (userId != null) {
						c.ne(u.userId, userId);
					}
				})

				.select(Expressions.count())

				.fetchOne() > 0;

	}

	/**
	 * @param entity
	 * @return affected rows
	 */
	@Insert
	int insert(AppUser entity);

	/**
	 * @param entity
	 * @return affected rows
	 */
	@Update
	int update(AppUser entity);

	/**
	 * @param entity
	 * @return affected rows
	 */
	@Delete
	int delete(AppUser entity);
}
