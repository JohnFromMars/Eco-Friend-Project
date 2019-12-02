package com.ecofriend.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecofriend.model.Provider;
import com.ecofriend.model.SiteUser;

/**
 * Provider Dao
 * 
 * @author user
 *
 */
@Repository
public interface ProviderDao extends CrudRepository<Provider, Long> {

	/**
	 * return the provider object based on the given site user
	 * 
	 * @param siteUser
	 * @return
	 */
	Provider findBySiteUser(SiteUser siteUser);

}
