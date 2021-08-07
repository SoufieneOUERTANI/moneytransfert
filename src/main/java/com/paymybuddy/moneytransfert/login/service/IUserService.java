package com.paymybuddy.moneytransfert.login.service;

import com.paymybuddy.moneytransfert.login.entity.User;
import com.paymybuddy.moneytransfert.login.user.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

	public User findByUserName(String userName);

	public void save(CrmUser crmUser);
}
