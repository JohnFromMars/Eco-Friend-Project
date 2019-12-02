package com.ecofriend.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecofriend.model.Depot;
import com.ecofriend.model.SiteUser;

/**
 * Depot Dao
 * 
 * @author user
 *
 */
@Repository
public interface DepotDao extends CrudRepository<Depot, Long> {

	/**
	 * Find the depot information in the database based on the site user
	 * information
	 * 
	 * @param user
	 * @return
	 */
	Depot findBySiteUser(SiteUser user);

}
