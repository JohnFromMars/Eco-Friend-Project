package com.ecofriend.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecofriend.model.Depot;
import com.ecofriend.model.SiteUser;

@Repository
public interface DepotDao extends CrudRepository<Depot, Long>{

	Depot findBySiteUser(SiteUser user);

}
