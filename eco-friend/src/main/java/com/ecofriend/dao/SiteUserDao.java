package com.ecofriend.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecofriend.model.SiteUser;

/**
 * Site user Dao
 * 
 * @author user
 *
 */
@Repository
public interface SiteUserDao extends CrudRepository<SiteUser, Long> {
	/**
	 * return the site user object based on the given email
	 * 
	 * @param email
	 * @return
	 */
	SiteUser findByEmail(String email);
}
