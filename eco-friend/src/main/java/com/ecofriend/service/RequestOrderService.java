package com.ecofriend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecofriend.dao.RequestOrderDao;
import com.ecofriend.model.RequestOrder;

/**
 * Request order service
 * 
 * @author user
 *
 */
@Service
public class RequestOrderService {
	@Autowired
	private RequestOrderDao requestOrderDao;

	/**
	 * Return the order based on the given order id
	 * 
	 * @param orderId
	 * @return
	 */
	public RequestOrder findOrder(long orderId) {
		return requestOrderDao.findOne(orderId);
	}

}
