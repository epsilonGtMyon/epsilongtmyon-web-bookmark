package epsilongtmyon.app.endpoint.usereditor.bl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import epsilongtmyon.app.common.appmessage.AppMessages;
import epsilongtmyon.app.common.appmessage.AppMessagesException;
import epsilongtmyon.app.common.db.dao.AppUserDao;
import epsilongtmyon.app.common.db.entity.AppUser;
import epsilongtmyon.app.common.security.auth.AppUserDetails;
import epsilongtmyon.app.common.util.StringUtil;
import epsilongtmyon.app.endpoint.usereditor.bl.spec.UserEditorInitResult;
import epsilongtmyon.app.endpoint.usereditor.bl.spec.UserEditorRegisterParam;

@Service
@Transactional
public class UserEditorService {

	private final AppUserDao appUserDao;

	private final PasswordEncoder passwordEncoder;

	public UserEditorService(AppUserDao appUserDao, PasswordEncoder passwordEncoder) {
		this.appUserDao = appUserDao;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * 初期処理
	 * @param userAliasId
	 * @return
	 */
	public UserEditorInitResult init(String userAliasId) {
		final UserEditorInitResult result = new UserEditorInitResult();

		final AppUser appUser = appUserDao.selectByUserAliasId(userAliasId);

		if (appUser == null) {
			throw AppMessagesException.fromStringMessage("ユーザーが存在しません。");
		} else {
			result.setUserId(appUser.userId);
			result.setUserAliasId(appUser.userAliasId);
			result.setUserName(appUser.userName);
			result.setMailAddress(appUser.mailAddress);
			result.setAdminFlag(appUser.adminFlag);
		}

		return result;
	}

	/**
	 * 登録します(新規/更新)
	 * @param param
	 * @return
	 */
	public Long register(UserEditorRegisterParam param) {
		boolean isNew = param.getUserId() == null;
		final AppMessages errors = new AppMessages();
		if (isNew) {
			if (StringUtil.isEmpty(param.getPassword())) {
				// 新規登録時はパスワード必須
				errors.addMessage("password", "新規登録時はパスワードは必須です。");
			}
		}

		final boolean dupilicateAppUser = appUserDao.existsByUserAliasId(param.getUserId(), param.getUserAliasId());
		if (dupilicateAppUser) {
			errors.addMessage("password", "IDは既に存在します。");
		}
		errors.throwIfHasMessage();

		//----------------------------------
		final AppUser appUser;
		if (isNew) {
			appUser = new AppUser();
		} else {
			appUser = appUserDao.selectById(param.getUserId());
			if (appUser == null) {
				throw AppMessagesException.fromStringMessage("ユーザーが存在しません。");
			}
		}

		appUser.userAliasId = param.getUserAliasId();
		appUser.userName = param.getUserName();
		appUser.mailAddress = param.getMailAddress();
		appUser.adminFlag = param.isAdminFlag();
		if (StringUtil.isNotEmpty(param.getPassword())) {
			//変更時のみ反映
			appUser.password = passwordEncoder.encode(param.getPassword());
		}

		if (isNew) {
			appUserDao.insert(appUser);
		} else {
			appUserDao.update(appUser);
		}

		return appUser.userId;
	}

	/**
	 * 削除します。
	 * @param userAliasId
	 */
	public void remove(Long userId, AppUserDetails loginUser) {
		final AppMessages errors = new AppMessages();
		if (userId.equals(loginUser.userId)) {
			errors.addMessage("自分を削除することはできません。");
		}

		errors.throwIfHasMessage();

		//-------------------------

		final AppUser appUser = appUserDao.selectById(userId);
		if (appUser == null) {
			throw AppMessagesException.fromStringMessage("ユーザーが存在しません。");
		}
		appUserDao.delete(appUser);
	}

}
