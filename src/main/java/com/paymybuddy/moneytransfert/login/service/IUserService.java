package com.paymybuddy.moneytransfert.login.service;

import com.paymybuddy.moneytransfert.login.entity.User;
import com.paymybuddy.moneytransfert.login.user.NewUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

	public User findByUserName(String userName);

	public User save(NewUser newUser);
}
