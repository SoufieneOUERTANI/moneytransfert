package com.paymybuddy.moneytransfert.login.dao;

import com.paymybuddy.moneytransfert.login.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
