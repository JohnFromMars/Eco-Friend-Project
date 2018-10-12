package com.ecofriend.dao;

import org.springframework.data.repository.CrudRepository;

import com.ecofriend.model.Sender;
import com.ecofriend.model.SiteUser;

public interface SenderDao extends CrudRepository<Sender, Long> {
	Sender findByUser(SiteUser user);

	Sender findByLicensNumber(String licenseNo);
}
