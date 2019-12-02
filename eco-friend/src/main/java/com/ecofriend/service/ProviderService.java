package com.ecofriend.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.ecofriend.dao.ProviderDao;
import com.ecofriend.dao.RequestOrderDao;
import com.ecofriend.dao.SiteUserDao;
import com.ecofriend.model.Provider;
import com.ecofriend.model.RequestOrder;
import com.ecofriend.model.SiteUser;

/**
 * Provider service
 * 
 * @author user
 *
 */
@Service
@Transactional
public class ProviderService {

	private final static int PAGE_SIZE = 30;

	@Autowired
	private ProviderDao providerDao;

	@Autowired
	private RequestOrderDao orderDao;

	@Autowired
	private SiteUserDao siteUserDao;

	/**
	 * update provider
	 * 
	 * @param provider
	 */
	public void updateProvider(Provider provider) {
		providerDao.save(provider);
	}

	/**
	 * Make an order and save it into database
	 * 
	 * @param order
	 * @param email
	 */
	public void order(RequestOrder order, String email) {
		SiteUser siteUser = siteUserDao.findByEmail(email);
		Provider provider = providerDao.findBySiteUser(siteUser);
		order.setProvider(provider);
		order.setAdded(new Date());
		orderDao.save(order);
	}

	/**
	 * Get a page of request order based on the page number and email
	 * 
	 * @param pageNumber
	 * @param email
	 * @return
	 */
	public Page<RequestOrder> getOrderPage(int pageNumber, String email) {
		SiteUser siteUser = siteUserDao.findByEmail(email);
		Provider provider = providerDao.findBySiteUser(siteUser);

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "added"), new Order(Direction.ASC, "confirm")));

		// return orderDao.findAll(request);
		return orderDao.findAllByProvider(request, provider);
	}

	/**
	 * Confirm the order based on the given order id
	 * 
	 * @param orderId
	 */
	public void confirmOrder(long orderId) {
		// TODO Auto-generated method stub
		RequestOrder order = orderDao.findOne(orderId);

		if (order.getSender() != null) {
			order.setConfirm(true);
		}

		orderDao.save(order);
	}

}
