package com.ecofriend.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecofriend.model.Depot;
import com.ecofriend.model.Provider;
import com.ecofriend.model.RequestOrder;
import com.ecofriend.model.Sender;

public interface RequestOrderDao extends PagingAndSortingRepository<RequestOrder, Long> {

	Page<RequestOrder> findAllByProvider(Pageable pageable, Provider provider);

	Page<RequestOrder> findAllBySender(Pageable pageable, Sender sender);

	Page<RequestOrder> findAllByDepotAndSender(Pageable pageable, Depot depot, Sender sender);

}
