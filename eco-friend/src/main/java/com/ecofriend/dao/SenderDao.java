package com.ecofriend.dao;

import org.springframework.data.repository.CrudRepository;

import com.ecofriend.model.Sender;
import com.ecofriend.model.SiteUser;
/**
 * Sender Dao
 * @author user
 *
 */
public interface SenderDao extends CrudRepository<Sender, Long> {
	/**
	 * return a sender object based on the given site user information
	 * @param user
	 * @return
	 */
	Sender findByUser(SiteUser user);

	/**
	 * return the sender object based on the given license number
	 * @param licenseNo
	 * @return
	 */
	Sender findByLicensNumber(String licenseNo);
}
