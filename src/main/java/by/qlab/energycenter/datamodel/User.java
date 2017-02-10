package by.qlab.energycenter.datamodel;

import java.util.HashSet;
import java.util.Set;

public class User {
	private long id;
	private String userLogin;
	private Set<Role> roles = new HashSet<Role>();

	public User() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
