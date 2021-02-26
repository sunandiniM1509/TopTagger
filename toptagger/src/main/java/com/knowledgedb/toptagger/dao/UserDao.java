package com.knowledgedb.toptagger.dao;

import com.knowledgedb.toptagger.entity.User;

public interface UserDao {

    public User findByUserName(String userName);
    
    public void save(User user);
    
}
