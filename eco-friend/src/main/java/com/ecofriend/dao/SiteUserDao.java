package com.ecofriend.dao;

import org.springframework.data.repository.CrudRepository;

import com.ecofriend.model.SiteUser;

public interface SiteUserDao extends CrudRepository<SiteUser, Long> {
	SiteUser findByEmail(String email);
}
