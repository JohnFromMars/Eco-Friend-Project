package com.ecofriend.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecofriend.model.Provider;
import com.ecofriend.model.SiteUser;

@Repository
public interface ProviderDao extends CrudRepository<Provider, Long> {

	Provider findBySiteUser(SiteUser siteUser);

}
