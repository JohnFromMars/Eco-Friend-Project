package com.ecofriend.service;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.ecofriend.dao.DepotDao;
import com.ecofriend.dao.RequestOrderDao;
import com.ecofriend.dao.SenderDao;
import com.ecofriend.dao.SiteUserDao;
import com.ecofriend.model.Depot;
import com.ecofriend.model.RequestOrder;
import com.ecofriend.model.Sender;
import com.ecofriend.model.SiteUser;

/**
 * Depot service
 * 
 * @author user
 *
 */
@Service
@Transactional
public class DepotService {
	private final static int PAGE_SIZE = 30;

	@Autowired
	private DepotDao depotDao;

	@Autowired
	private RequestOrderDao requestOrderDao;

	@Autowired
	private SenderDao senderDao;

	@Autowired
	private SiteUserDao siteUserDao;

	/**
	 * Update the depot information
	 * 
	 * @param depot
	 */
	public void updateDepot(Depot depot) {
		depotDao.save(depot);
	}

	/**
	 * Search sender's order based on his/her license number
	 * 
	 * @param licenseNo
	 * @param pageNumber
	 * @return
	 */
	public Page<RequestOrder> searchOrder(String licenseNo, int pageNumber) {

		Sender sender = senderDao.findByLicensNumber(licenseNo);
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, new Sort(new Order(Direction.ASC, "added")));
		return requestOrderDao.findAllByDepotAndSender(request, null, sender);
	}

	/**
	 * return the order that hasn't been completed
	 * 
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public RequestOrder preRegisterOrder(long orderId) throws Exception {
		RequestOrder order = requestOrderDao.findOne(orderId);

		if (!order.isConfirm() || order.getSender() == null || order.getDepot() != null) {
			throw new Exception();
		}

		return order;
	}

	/**
	 * Save the order
	 * 
	 * @param order
	 */
	public void postRegisterOrder(RequestOrder order) {
		requestOrderDao.save(order);
	}

	/**
	 * Register the incentive for the request order
	 * 
	 * @param order
	 * @param email
	 * @param orderId
	 * @throws Exception
	 */
	public void registerIncentive(RequestOrder order, String email, long orderId) throws Exception {

		RequestOrder oriOrder = requestOrderDao.findOne(orderId);

		if (!oriOrder.isConfirm() || oriOrder.getSender() == null || oriOrder.getDepot() != null) {
			throw new Exception();
		}

		SiteUser user = siteUserDao.findByEmail(email);
		Depot depot = depotDao.findBySiteUser(user);

		oriOrder.setTotalContainerNo(order.getTotalContainerNo());
		oriOrder.setValidContainerNo(order.getValidContainerNo());
		oriOrder.setTotalIncentive(order.getTotalIncentive());
		oriOrder.setDepot(depot);
		oriOrder.setEcoIncentive(order.getTotalIncentive().multiply(new BigDecimal(0.1)));
		oriOrder.setSenderIncentive(order.getTotalIncentive().multiply(new BigDecimal(0.7)));
		oriOrder.setProviderIncentive(order.getTotalIncentive().multiply(new BigDecimal(0.2)));

		requestOrderDao.save(oriOrder);
		System.out.println("====================");
		System.out.println(order);
		System.out.println(oriOrder);
	}

}
