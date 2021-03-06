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

import com.ecofriend.model.RequestOrder;
import com.ecofriend.model.Sender;
import com.ecofriend.model.SiteUser;
import com.ecofriend.service.SenderService;
import com.ecofriend.service.SiteUserService;

/**
 * Sender controller
 * 
 * @author user
 *
 */
@Controller
public class SenderController {

	@Autowired
	private SiteUserService siteUserService;

	@Autowired
	private SenderService senderService;

	private String getUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

	/**
	 * return the update sender page to the user
	 * 
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "/update_sender", method = RequestMethod.GET)
	public ModelAndView updateSender(ModelAndView modelAndView) {

		Sender sender = new Sender();
		modelAndView.getModel().put("sender", sender);
		modelAndView.setViewName("app.update_sender");
		return modelAndView;
	}

	/**
	 * receive and save the sender information
	 * 
	 * @param modelAndView
	 * @param sender
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/update_sender", method = RequestMethod.POST)
	public ModelAndView updateSender(ModelAndView modelAndView, @Valid Sender sender, BindingResult result) {

		modelAndView.setViewName("app.update_sender");

		if (!result.hasErrors()) {
			String email = getUserName();
			SiteUser siteUser = siteUserService.findUserByEmail(email);
			sender.setSiteUser(siteUser);

			senderService.UpdateSender(sender);
			modelAndView.setViewName("redirect:/");
		}

		return modelAndView;
	}

	/**
	 * view the order that the sender has taken
	 * 
	 * @param modelAndView
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value = "/sender_order", method = RequestMethod.GET)
	public ModelAndView viewOrder(ModelAndView modelAndView,
			@RequestParam(name = "p", defaultValue = "1") int pageNumber) {

		String email = getUserName();
		Page<RequestOrder> page = senderService.viewOrder(pageNumber, email);

		modelAndView.getModel().put("page", page);
		modelAndView.setViewName("app.sender_order");

		return modelAndView;
	}

	/**
	 * view the order that sender has not taken
	 * 
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "/find_order", method = RequestMethod.GET)
	public ModelAndView findOrder(ModelAndView modelAndView,
			@RequestParam(name = "p", defaultValue = "1") int pageNumber) {

		Page<RequestOrder> page = senderService.findOrder(pageNumber);
		modelAndView.getModel().put("page", page);
		modelAndView.setViewName("app.find_order");
		return modelAndView;
	}

	/**
	 * sender pick the order to collect
	 * 
	 * @param modelAndView
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/pick_order", method = RequestMethod.GET)
	public ModelAndView pickOrder(ModelAndView modelAndView, @RequestParam(name = "id") long orderId) {
		String email = getUserName();

		senderService.pickOrder(email, orderId);
		modelAndView.setViewName("redirect:/find_order");

		return modelAndView;
	}

}
