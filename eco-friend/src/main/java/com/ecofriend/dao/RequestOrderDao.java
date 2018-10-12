package com.ecofriend.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecofriend.model.Provider;
import com.ecofriend.model.RequestOrder;

public interface RequestOrderDao extends PagingAndSortingRepository<RequestOrder, Long> {

	Page<RequestOrder> findAllByProvider(Pageable pageable, Provider provider);

}
