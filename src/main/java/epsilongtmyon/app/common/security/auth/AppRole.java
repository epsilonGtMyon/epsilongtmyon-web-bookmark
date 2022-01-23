package epsilongtmyon.app.common.security.auth;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum AppRole {

	ADMIN("ROLE_ADMIN"),

	;

	public final String roleName;

	private AppRole(String roleName) {
		this.roleName = roleName;
	}

	public SimpleGrantedAuthority asGrantedAuthority() {
		return new SimpleGrantedAuthority(roleName);
	}

	public String getShortRoleName() {
		//ROLE_以降
		return roleName.substring(5);
	}
}
