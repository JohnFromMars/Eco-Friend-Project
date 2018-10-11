package com.ecofriend.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecofriend.model.Depot;

@Repository
public interface DepotDao extends CrudRepository<Depot, Long>{

}
