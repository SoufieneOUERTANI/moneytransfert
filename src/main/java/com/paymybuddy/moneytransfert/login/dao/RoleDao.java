package com.paymybuddy.moneytransfert.login.dao;

import com.paymybuddy.moneytransfert.login.entity.Role;

import java.util.List;

public interface RoleDao {

	public List<Role> findAll();

	public Role findRoleByName(String theRoleName);

	public Role save(Role role);

}
