package com.paymybuddy.moneytransfert.service;

import com.paymybuddy.moneytransfert.entity.User;
import com.paymybuddy.moneytransfert.user.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

	public void save(CrmUser crmUser);
}
