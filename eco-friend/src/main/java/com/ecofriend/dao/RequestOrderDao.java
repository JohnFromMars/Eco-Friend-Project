package com.ecofriend.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecofriend.model.Depot;
import com.ecofriend.model.Provider;
import com.ecofriend.model.RequestOrder;
import com.ecofriend.model.Sender;

/**
 * Request Order Dao
 * 
 * @author user
 *
 */
public interface RequestOrderDao extends PagingAndSortingRepository<RequestOrder, Long> {

	/**
	 * Return a page of request order based on given provider information
	 * 
	 * @param pageable
	 * @param provider
	 * @return
	 */
	Page<RequestOrder> findAllByProvider(Pageable pageable, Provider provider);

	/**
	 * Return a page of request orders based on the given sender information
	 * 
	 * @param pageable
	 * @param sender
	 * @return
	 */
	Page<RequestOrder> findAllBySender(Pageable pageable, Sender sender);

	/**
	 * Return a page of request orders based on the given sender
	 * @param pageable
	 * @param depot
	 * @param sender
	 * @return
	 */
	Page<RequestOrder> findAllByDepotAndSender(Pageable pageable, Depot depot, Sender sender);

}
