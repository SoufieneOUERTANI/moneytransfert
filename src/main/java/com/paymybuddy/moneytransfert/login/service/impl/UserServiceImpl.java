package com.paymybuddy.moneytransfert.login.service.impl;

import com.paymybuddy.moneytransfert.login.dao.RoleDao;
import com.paymybuddy.moneytransfert.login.dao.UserDao;
import com.paymybuddy.moneytransfert.login.entity.Role;
import com.paymybuddy.moneytransfert.login.entity.User;
import com.paymybuddy.moneytransfert.login.service.IUserService;
import com.paymybuddy.moneytransfert.login.user.NewUser;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

	private static final Logger logger = LogManager.getLogger("UserServiceImpl");

	// need to inject user dao
	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public User findByUserName(String userName) {
		// check the database if the user already exists
		return userDao.findByUserName(userName);
	}

	@Override
	@Transactional
	public User save(NewUser newUser) {
		if (newUser.getPassword() != newUser.getMatchingPassword()) {
			throw new RuntimeException("La confirmation du mot de passe n'est pas identique");
		}
		User user = new User();
		 // assign user details to the user object
		user.setUserName(newUser.getUserName());
		user.setPassword(passwordEncoder.encode(newUser.getPassword()));
		user.setFirstName(newUser.getFirstName());
		user.setLastName(newUser.getLastName());
		user.setEmail(newUser.getEmail());

		// give user default role of "employee"
		logger.info("roleDao.findRoleByName(\"ROLE_EMPLOYEE\") : "+roleDao.findRoleByName("ROLE_EMPLOYEE"));
		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_EMPLOYEE")));

		 // save user in the database
		userDao.save(user);
		return(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
