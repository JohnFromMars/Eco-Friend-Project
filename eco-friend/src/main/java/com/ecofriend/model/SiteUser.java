package com.ecofriend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ecofriend.enums.Role;

@Entity
@Table(name = "user")
public class SiteUser {

	@Id
	@Column(name = "userId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;

	@Column(name = "email")
	private String email;

	@Column(name = "role")
	private Role role;

	@Column(name = "password")
	private String password;

	@Transient
	private String plainPassword;

	@Transient
	private String repeatPassword;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
