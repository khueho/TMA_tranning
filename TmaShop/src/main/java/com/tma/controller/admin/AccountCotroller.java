package com.tma.controller.admin;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tma.dto.AccountDto;
import com.tma.entity.Account;
import com.tma.entity.Category;
import com.tma.service.AccountService;
import com.tma.service.CategoryService;

@Controller
@RequestMapping("admin/accounts")
public class AccountCotroller {
	@Autowired
	AccountService  accountService;
	
	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("account", new AccountDto());
		return "admin/accounts/addOrEdit";
	}
	
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model ,
			@Valid @ModelAttribute ("account")	AccountDto dto , BindingResult result) {
		if(result.hasErrors()){
			return new ModelAndView("admin/accounts/addOrEdit");
		}
		Account entity = new Account();
		BeanUtils.copyProperties(dto, entity);
		accountService.save(entity);
		model.addAttribute("message" , "account is save !");
		return new ModelAndView( "forward:/admin/accounts",model);
	}
	
	
	@RequestMapping("")
	public String  list(ModelMap model) {
		List<Account> list = accountService.findAll();
		model.addAttribute("accounts", list);
		return "admin/accounts/list";
		
	}
	
	
	@GetMapping("edit/{username}")
	public ModelAndView edit(ModelMap model , @PathVariable ("username") String username ) {
		Optional<Account> opt = accountService.findById(username);
		AccountDto dto = new AccountDto();
		 if (opt.isPresent()) {
			 Account  entity = opt.get();
			
		BeanUtils.copyProperties(entity, dto);
		dto.setIsEdit(true);
		dto.setPassword("");
		model.addAttribute("account" , dto);
		return new ModelAndView("admin/accounts/addOrEdit",model);
		}
		 model.addAttribute("message","account is not existed");
		 return new ModelAndView( "forward:/admin/accounts",model);
	}
	
	@GetMapping("delete/{username}")
	public ModelAndView  delete(ModelMap model , @PathVariable ("username") String username) {
		accountService.deleteById(username);
		model.addAttribute("message" , "account is delete");
		return new ModelAndView("forward:/admin/accounts",model);	
	}
//	
//	@PostMapping("saveOrUpdate")
//	public ModelAndView saveOrUpdate(ModelMap model ,
//			@Valid @ModelAttribute ("account")	AccountDto dto , BindingResult result) {
//		if(result.hasErrors()){
//			return new ModelAndView("admin/categories/addOrEdit");
//		}
//		Category entity = new Category();
//		BeanUtils.copyProperties(dto, entity);
//		accountService.save(entity);
//		model.addAttribute("message" , "account is save !");
//		return new ModelAndView( "forward:/admin/categories",model);
//	}
//	

//	@GetMapping("search")
//	public String search(ModelMap model , @RequestParam(name ="name" , required = false) String name) {
//		List<Category> list = null ;
//		if (StringUtils.hasText(name)) {
//			list = accountService.findByNameContaining(name);
//		
//		}
//		else {
//			list = accountService.findAll();
//		}
//		model.addAttribute("categories", list);
//		return "admin/categories/search";
//	}
//	

}
