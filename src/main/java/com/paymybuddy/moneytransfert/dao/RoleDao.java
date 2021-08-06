package com.paymybuddy.moneytransfert.dao;

import com.paymybuddy.moneytransfert.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
