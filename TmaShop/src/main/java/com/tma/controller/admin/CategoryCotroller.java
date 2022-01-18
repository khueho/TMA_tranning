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

import com.tma.dto.CategoryDto;
import com.tma.entity.Category;
import com.tma.service.CategoryService;

@Controller
@RequestMapping("admin/categories")
public class CategoryCotroller {
	@Autowired
	CategoryService  categoryService;
	
	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("category", new CategoryDto());
		return "admin/categories/addOrEdit";
	}
	
	@GetMapping("edit/{categoryId}")
	public ModelAndView edit(ModelMap model , @PathVariable ("categoryId") Long categoryId ) {
		Optional<Category> opt = categoryService.findById(categoryId);
		CategoryDto dto = new CategoryDto();
		 if (opt.isPresent()) {
			Category  entity = opt.get();
			
		BeanUtils.copyProperties(entity, dto);
		dto.setIsEdit(true);
		model.addAttribute("category" , dto);
		return new ModelAndView("admin/categories/addOrEdit",model);
		}
		 model.addAttribute("message","category is not existed");
		 return new ModelAndView( "forward:/admin/categories",model);
	}
	
	@GetMapping("delete/{categoryId}")
	public ModelAndView  delete(ModelMap model , @PathVariable ("categoryId") Long categoryId) {
		categoryService.deleteById(categoryId);
		model.addAttribute("message" , "category is delete");
		return new ModelAndView("forward:/admin/categories/search",model);	
	}
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model ,
			@Valid @ModelAttribute ("category")	CategoryDto dto , BindingResult result) {
		if(result.hasErrors()){
			return new ModelAndView("admin/categories/addOrEdit");
		}
		Category entity = new Category();
		BeanUtils.copyProperties(dto, entity);
		categoryService.save(entity);
		model.addAttribute("message" , "category is save !");
		return new ModelAndView( "forward:/admin/categories",model);
	}
	
	@RequestMapping("")
	public String  list(ModelMap model) {
		List<Category> list = categoryService.findAll();
		model.addAttribute("categories", list);
		return "admin/categories/list";
		
	}
	@GetMapping("search")
	public String search(ModelMap model , @RequestParam(name ="name" , required = false) String name) {
		List<Category> list = null ;
		if (StringUtils.hasText(name)) {
			list = categoryService.findByNameContaining(name);
		
		}
		else {
			list = categoryService.findAll();
		}
		model.addAttribute("categories", list);
		return "admin/categories/search";
	}
	

}
