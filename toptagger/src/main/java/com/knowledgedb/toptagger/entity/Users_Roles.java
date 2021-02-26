package com.knowledgedb.toptagger.entity;

import javax.persistence.*;

@Entity
@Table(name = "users_roles")
public class Users_Roles { 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int user_id;

	@Column(name = "role_id")
	private int role_id;

	public Users_Roles() {
	}

	public Users_Roles(int user_id) {
		super();
		this.user_id = user_id;
		this.role_id = role_id;
	}

	public int getUserId() {
		return user_id;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;
	}

	public int getRoleId() {
		return role_id;
	}

	public void setRoleId(int role_id) {
		this.role_id = role_id;
	}

	@Override
	public String toString() {
		return "Users_Roles{" + "user_id=" + user_id + ", role_id='" + role_id + '\'' + '}';
	}
}
