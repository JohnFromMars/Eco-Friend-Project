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

import com.ecofriend.model.Depot;
import com.ecofriend.model.RequestOrder;
import com.ecofriend.model.SiteUser;
import com.ecofriend.service.DepotService;
import com.ecofriend.service.SiteUserService;

/**
 * Depot center controller
 * 
 * @author user
 *
 */
@Controller
public class DepotController {

	@Autowired
	private DepotService depotService;

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

	/**
	 * Update depot page get request
	 * 
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "/update_depot", method = RequestMethod.GET)
	public ModelAndView updateSender(ModelAndView modelAndView) {
		Depot depot = new Depot();

		modelAndView.getModel().put("depot", depot);
		modelAndView.setViewName("app.update_depot");
		return modelAndView;
	}

	/**
	 * Update depot post request
	 * 
	 * @param modelAndView
	 * @param depot
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/update_depot", method = RequestMethod.POST)
	public ModelAndView updateSender(ModelAndView modelAndView, @Valid Depot depot, BindingResult result) {

		modelAndView.setViewName("app.update_depot");

		if (!result.hasErrors()) {
			String email = getUserName();
			SiteUser siteUser = siteUserService.findUserByEmail(email);
			depot.setSiteUser(siteUser);

			depotService.updateDepot(depot);
			modelAndView.setViewName("redirect:/");
		}

		return modelAndView;
	}

	/**
	 * get request for search order page
	 * 
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "/search_order", method = RequestMethod.GET)
	public ModelAndView searchOrder(ModelAndView modelAndView) {
		modelAndView.setViewName("app.search_order");
		return modelAndView;
	}

	/**
	 * Find the search result of orders that related to the sender based on
	 * given driving license number return result of search
	 * 
	 * @param modelAndView
	 * @param licenseNo
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value = "/search_result", method = RequestMethod.GET)
	public ModelAndView searchResult(ModelAndView modelAndView, @RequestParam(name = "s") String licenseNo,
			@RequestParam(name = "p", defaultValue = "1") int pageNumber) {

		Page<RequestOrder> page = depotService.searchOrder(licenseNo, pageNumber);
		modelAndView.getModel().put("page", page);
		modelAndView.setViewName("app.search_result");
		return modelAndView;
	}

	/**
	 * return the register incentive page to the user
	 * 
	 * @param modelAndView
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/register_incentive", method = RequestMethod.GET)
	public ModelAndView registerIncentive(ModelAndView modelAndView, @RequestParam(name = "id") long orderId)
			throws Exception {

		RequestOrder order = new RequestOrder();

		modelAndView.getModel().put("order", order);
		modelAndView.setViewName("app.register_incentive");

		return modelAndView;
	}

	/**
	 * Post request to register the incentive for given order
	 * 
	 * @param modelAndView
	 * @param order
	 * @param result
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/register_incentive", method = RequestMethod.POST)
	public ModelAndView registerIncentive(ModelAndView modelAndView, @Valid RequestOrder order, BindingResult result,
			@RequestParam(name = "id") long orderId) throws Exception {

		RequestOrder oriOrder = depotService.preRegisterOrder(orderId);

		modelAndView.setViewName("app.register_incentive");

		if (!result.hasErrors()) {
			String email = getUserName();
			depotService.registerIncentive(order, email, orderId);
			modelAndView.setViewName("redirect:/search_result?s=" + oriOrder.getSender().getLicensNumber());
		}

		return modelAndView;
	}

}
