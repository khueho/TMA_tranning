package com.tma.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tma.dto.AccountDto;
import com.tma.dto.LoginAdminDto;
import com.tma.entity.Account;
import com.tma.service.AccountService;

@Controller
public class AdminLoginController {
	@Autowired
	private	HttpSession  session;
	
	@Autowired
	AccountService  accountService;
	
	@GetMapping("alogin")
	public String login(ModelMap model) {
		model.addAttribute("account", new LoginAdminDto());
		return "admin/accounts/login";
	}
	
	@PostMapping("alogin")
	public ModelAndView login(ModelMap model ,
			@Valid @ModelAttribute ("account")	LoginAdminDto dto , BindingResult result) {
		if(result.hasErrors()){
			return new ModelAndView("admin/accounts/login", model);
		}
		
		Account account = accountService.login(dto.getUsername(), dto.getPassword());
		
		if (account== null) {
			model.addAttribute("message","invalid username or password");
			return new ModelAndView("admin/accounts/login", model);
		}
		
		session.setAttribute("username",account.getUsername());
		Object ruri = session.getAttribute("redirect-uri");
		if (ruri != null) {
			session.removeAttribute("redirect-uri");
			return new ModelAndView("redirect:" + ruri);
		}
		
		
		return new ModelAndView( "forward:/admin/accounts",model);
	}

}

