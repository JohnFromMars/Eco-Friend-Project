package com.ecofriend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecofriend.dao.DepotDao;
import com.ecofriend.model.Depot;

@Service
public class DepotService {

	@Autowired
	private DepotDao depotDao;

	// update depot profile
	public void updateDepot(Depot depot) {
		depotDao.save(depot);
	}

}
