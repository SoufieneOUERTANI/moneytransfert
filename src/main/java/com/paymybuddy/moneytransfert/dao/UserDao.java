package com.paymybuddy.moneytransfert.dao;

import com.paymybuddy.moneytransfert.entity.User;

public interface UserDao {

    public User findByUserName(String userName);
    
    public void save(User user);
    
}
