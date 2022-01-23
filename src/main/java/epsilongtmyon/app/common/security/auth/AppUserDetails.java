package epsilongtmyon.app.common.security.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class AppUserDetails implements UserDetails, CredentialsContainer {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	public Long userId;

	@Column(name = "user_alias_id")
	public String userAliasId;

	@Column(name = "user_name")
	public String userName;

	@Column(name = "password")
	public String password;

    /** 管理者フラグ */
    @Column(name = "admin_flag")
    public Boolean adminFlag;

	//--------------
	//UserDetails

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		final List<GrantedAuthority> authorities = new ArrayList<>();
		if(adminFlag.booleanValue()) {
			authorities.add(AppRole.ADMIN.asGrantedAuthority());
		}

		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userAliasId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	//-----------
	//CredentialsContainer

	@Override
	public void eraseCredentials() {
		password = null;
	}

}
