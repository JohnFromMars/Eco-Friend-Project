package com.ecofriend.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecofriend.model.SiteUser;

@Repository
public interface SiteUserDao extends CrudRepository<SiteUser, Long> {
	SiteUser findByEmail(String email);
}
