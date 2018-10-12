package com.ecofriend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ecofriend.model.Provider;
import com.ecofriend.model.RequestOrder;
import com.ecofriend.model.SiteUser;
import com.ecofriend.service.ProviderService;
import com.ecofriend.service.SiteUserService;

@Controller
public class ProviderController {

	@Autowired
	private ProviderService providerService;

	@Autowired
	private SiteUserService siteUserService;

	/**
	 * get user name from security
	 * 
	 * @return
	 */
	private String getUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

	// update get
	@RequestMapping(value = "/update_provider", method = RequestMethod.GET)
	public ModelAndView updateProvider(ModelAndView modelAndView) {
		Provider provider = new Provider();

		modelAndView.getModel().put("provider", provider);
		modelAndView.setViewName("app.update_provider");
		return modelAndView;
	}

	// update post save
	@RequestMapping(value = "/update_provider", method = RequestMethod.POST)
	public ModelAndView updateProvider(ModelAndView modelAndView, @Valid Provider provider, BindingResult result) {

		modelAndView.setViewName("app.update_provider");

		if (!result.hasErrors()) {
			String email = getUserName();
			SiteUser siteUser = siteUserService.findUserByEmail(email);
			provider.setSiteUser(siteUser);

			providerService.updateProvider(provider);
			modelAndView.setViewName("redirect:/");
		}

		return modelAndView;
	}

	/**
	 * make order page - GET
	 * 
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public ModelAndView order(ModelAndView modelAndView) {
		RequestOrder order = new RequestOrder();
		modelAndView.getModel().put("order", order);
		modelAndView.setViewName("app.order");
		return modelAndView;
	}

	/**
	 * make order page - POST
	 * 
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public ModelAndView order(ModelAndView modelAndView, @Valid RequestOrder order, BindingResult result) {

		modelAndView.setViewName("app.order");

		if (!result.hasErrors()) {
			String email = getUserName();
			providerService.order(order, email);
			modelAndView.setViewName("redirect:/");
		}

		return modelAndView;
	}

	/**
	 * view order for provider GET
	 * 
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "/provider_order", method = RequestMethod.GET)
	public ModelAndView viewOrder(ModelAndView modelAndView,
			@RequestParam(name = "p", defaultValue = "1") int pageNumber) {

		String email = getUserName();
		Page<RequestOrder> page = providerService.getOrderPage(pageNumber, email);

		modelAndView.getModel().put("page", page);
		modelAndView.setViewName("app.provider_order");

		return modelAndView;
	}

	/**
	 * confirm the order after seeing the sender
	 * 
	 * @return
	 */
	@RequestMapping(value = "/confirm_order", method = RequestMethod.GET)
	public ModelAndView confirmOrder(ModelAndView modelAndView, @RequestParam(name = "id") long orderId) {
		
		providerService.confirmOrder(orderId);
		modelAndView.setViewName("redirect:/provider_order");
		return modelAndView;
	}

}
