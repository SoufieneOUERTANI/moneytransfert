package com.paymybuddy.moneytransfert.login.dao;

import com.paymybuddy.moneytransfert.login.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

	private static final Logger logger = LogManager.getLogger("RoleDaoImpl");

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Role> findAll() {

		Session currentSession = entityManager.unwrap(Session.class);

		Query<Role> theQuery = currentSession.createQuery("from Role", Role.class);

		List <Role> roles = null;

		try {
			roles = theQuery.getResultList();
		} catch (Exception e) {
			roles = null;
		}

		logger.info("roles : " + roles);

		return roles;

	}

	@Override
	public Role findRoleByName(String theRoleName) {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		logger.info("theRoleName : " + theRoleName);

		// now retrieve/read from database using name
		Query<Role> theQuery = currentSession.createQuery("from Role where name=:roleName", Role.class);
		theQuery.setParameter("roleName", theRoleName);


		Role theRole = null;
		
		try {
			theRole = theQuery.getSingleResult();
		} catch (Exception e) {
			theRole = null;
		}
		logger.info("theRole : " + theRole);

		return theRole;
	}

	@Override
	public Role save(Role role) {

		Session currentSession = entityManager.unwrap(Session.class);

		Role theRole;

		try {
			theRole = (Role) currentSession.save(role);

		} catch (Exception e) {
			theRole = null;
		}

		return theRole;
	}
}
