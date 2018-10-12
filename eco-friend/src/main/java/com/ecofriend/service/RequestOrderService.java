package com.ecofriend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecofriend.dao.RequestOrderDao;
import com.ecofriend.model.RequestOrder;

@Service
public class RequestOrderService {
	@Autowired
	private RequestOrderDao requestOrderDao;

	public RequestOrder findOrder(long orderId) {
		return requestOrderDao.findOne(orderId);
	}

}
