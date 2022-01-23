package epsilongtmyon.app.endpoint.userlist.bl.spec.dto;

import org.seasar.doma.Entity;
import org.seasar.doma.jdbc.entity.NamingType;

@Entity(naming = NamingType.SNAKE_LOWER_CASE)
public class UserDto {

	/**
	 * ユーザー別名ID
	 */
	public String userAliasId;

	/**
	 * ユーザー名
	 */
	public String userName;

	/**
	 * メールアドレス
	 */
	public String mailAddress;

	/**
	 * 管理者フラグ
	 */
	public boolean adminFlag;
}
