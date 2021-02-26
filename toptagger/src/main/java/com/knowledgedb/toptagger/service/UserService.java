package com.knowledgedb.toptagger.service;

import com.knowledgedb.toptagger.entity.User;
import com.knowledgedb.toptagger.user.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

	public void save(CrmUser crmUser);
}
