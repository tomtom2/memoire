package com.github.tbrien.gentest.web.controller;

import javax.inject.Inject;

import org.apache.ivy.osgi.core.BundleInfoAdapter.ProfileNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

//	@Inject
//	private RecommendationFacade facade;

	@RequestMapping({ "/", "index.html" })
	public String page(Model model) {
		model.addAttribute("a", 0.0);
		model.addAttribute("b", 0.0);
		model.addAttribute("c", 0.0);
		return "index";
	}

	@RequestMapping("/solve")
	public String pageSolved(@RequestParam("a") double a,
			@RequestParam("b") double b, @RequestParam("c") double c,
			Model model) throws ProfileNotFoundException {
		model.addAttribute("a", a);
		model.addAttribute("b", b);
		model.addAttribute("c", c);

		if (a != 0) {
			model.addAttribute("sol1", a);
			model.addAttribute("sol2", b);
		} else if (b != 0) {
			model.addAttribute("sol0", -(c/b));
		}

		return "index";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

}