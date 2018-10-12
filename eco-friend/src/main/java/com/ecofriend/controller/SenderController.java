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

	// update get
	@RequestMapping(value = "/update_sender", method = RequestMethod.GET)
	public ModelAndView updateSender(ModelAndView modelAndView) {

		Sender sender = new Sender();
		modelAndView.getModel().put("sender", sender);
		modelAndView.setViewName("app.update_sender");
		return modelAndView;
	}

	// update post save
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

	@RequestMapping(value = "/sender_order", method = RequestMethod.GET)
	public ModelAndView viewOrder(ModelAndView modelAndView,
			@RequestParam(name = "p", defaultValue = "1") int pageNumber) {

		String email = getUserName();

		Page<RequestOrder> page = senderService.viewOrder(pageNumber, email);

		System.out.println("================");
		System.out.println(page.getNumberOfElements());
		System.out.println("================");

		modelAndView.getModel().put("page", page);
		modelAndView.setViewName("app.sender_order");

		return modelAndView;
	}

}
