package com.ecofriend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecofriend.dao.SenderDao;
import com.ecofriend.model.Sender;

@Service
public class SenderService {

	@Autowired
	private SenderDao senderDao;

	/**
	 * update sender profile
	 * @param sender
	 */
	public void UpdateSender(Sender sender){
		senderDao.save(sender);
	}
}
