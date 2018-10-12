package com.ecofriend.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.ecofriend.dao.RequestOrderDao;
import com.ecofriend.dao.SenderDao;
import com.ecofriend.dao.SiteUserDao;
import com.ecofriend.model.RequestOrder;
import com.ecofriend.model.Sender;
import com.ecofriend.model.SiteUser;

@Service
@Transactional
public class SenderService {
	private final static int PAGE_SIZE = 30;

	@Autowired
	private SenderDao senderDao;

	@Autowired
	private SiteUserDao siteUserDao;

	@Autowired
	private RequestOrderDao requestOrderDao;

	/**
	 * update sender profile
	 * 
	 * @param sender
	 */
	public void UpdateSender(Sender sender) {
		senderDao.save(sender);
	}

	public Page<RequestOrder> viewOrder(int pageNumber, String email) {
		SiteUser siteUser = siteUserDao.findByEmail(email);
		Sender sender = senderDao.findByUser(siteUser);

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, new Sort(new Order(Direction.DESC, "added")));

		return requestOrderDao.findAllBySender(request, sender);
	}

	public Page<RequestOrder> findOrder(int pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, new Sort(new Order(Direction.ASC, "added")));

		// find the order with no sender
		return requestOrderDao.findAllBySender(request, null);
	}

	/**
	 * set the sender of order to user
	 * 
	 * @param email
	 * @param orderId
	 */
	public void pickOrder(String email, long orderId) {
		SiteUser user = siteUserDao.findByEmail(email);
		Sender sender = senderDao.findByUser(user);
		RequestOrder order = requestOrderDao.findOne(orderId);

		if (order.getSender() == null) {
			order.setSender(sender);
		}

		requestOrderDao.save(order);
	}
}
