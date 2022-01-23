package epsilongtmyon.app.common.db.dao;

import java.util.List;

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

import epsilongtmyon.app.common.db.entity.Bookmark;
import epsilongtmyon.app.common.db.entity.Bookmark_;

/**
 */
@Dao
@ConfigAutowireable
public interface BookmarkDao {

	/**
	 * @param bookmarkId
	 * @return the Bookmark entity
	 */
	@Select
	Bookmark selectById(Long bookmarkId);

	default List<Bookmark> selectByUserId(Long userId) {
		Config config = Config.get(this);
		Entityql entityql = new Entityql(config);

		Bookmark_ b = new Bookmark_();

		return entityql.from(b)
				.where(c -> c.eq(b.userId, userId))

				.orderBy(c -> c.asc(b.userListOrder))

				.fetch();
	}

	default Integer getMaxListOrderByUserId(Long userId) {
		Config config = Config.get(this);
		NativeSql nativeSql = new NativeSql(config);

		Bookmark_ b = new Bookmark_();

		return nativeSql.from(b)

				.where(c -> c.eq(b.userId, userId))

				.select(Expressions.max(b.userListOrder))

				.fetchOptional()

				.orElse(0)

		;

	}

	/**
	 * @param entity
	 * @return affected rows
	 */
	@Insert
	int insert(Bookmark entity);

	/**
	 * @param entity
	 * @return affected rows
	 */
	@Update
	int update(Bookmark entity);

	/**
	 * @param entity
	 * @return affected rows
	 */
	@Delete
	int delete(Bookmark entity);
}
