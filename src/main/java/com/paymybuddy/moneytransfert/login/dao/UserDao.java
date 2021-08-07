package com.paymybuddy.moneytransfert.login.dao;

import com.paymybuddy.moneytransfert.login.entity.User;

public interface UserDao {

    public User findByUserName(String userName);
    
    public void save(User user);
    
}
