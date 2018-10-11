package com.ecofriend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecofriend.dao.ProviderDao;
import com.ecofriend.model.Provider;

@Service
public class ProviderService {

	@Autowired
	private ProviderDao providerDao;

	// update provider
	public void updateProvider(Provider provider) {
		providerDao.save(provider);
	}

}
