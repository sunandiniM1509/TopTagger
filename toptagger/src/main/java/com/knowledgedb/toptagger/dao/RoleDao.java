package com.knowledgedb.toptagger.dao;

import com.knowledgedb.toptagger.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
