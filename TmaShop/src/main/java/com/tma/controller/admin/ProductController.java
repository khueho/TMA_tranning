package com.tma.controller.admin;

import java.io.File;
import java.io.IOException;
import java.lang.StackWalker.Option;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.mail.imap.protocol.Item;
import com.tma.dto.CategoryDto;
import com.tma.dto.ProductDto;
import com.tma.entity.Category;
import com.tma.entity.Product;
import com.tma.service.CategoryService;
import com.tma.service.ProductService;
import com.tma.service.StorageService;

@Controller
@RequestMapping("admin/products")
public class ProductController {
	@Autowired
	CategoryService  categoryService;
	
	@Autowired
	ProductService  productService;
	
	@Autowired
	StorageService  storageService;
	
	@ModelAttribute("categories")
	public List<CategoryDto> getcategory() {
		return categoryService.findAll().stream().map(item -> {
			CategoryDto dto = new CategoryDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("add")
	public String add(Model model) {
		ProductDto dto = new ProductDto();
		dto.setIsEdit(false);
		model.addAttribute("product", dto);
		
		return "admin/products/addOrEdit";
	}
	
	@GetMapping("edit/{productId}")
	public ModelAndView edit(ModelMap model , @PathVariable ("productId") Long productId ) {
		Optional<Product> opt = productService.findById(productId);
		ProductDto dto = new ProductDto();
		 if (opt.isPresent()) {
			 Product  entity = opt.get();
			
		BeanUtils.copyProperties(entity, dto);
		dto.setCategoryId(entity.getCategory().getCategoryId());
		dto.setIsEdit(true);
		model.addAttribute("product" , dto);
		return new ModelAndView("admin/products/addOrEdit",model);
		}
		 model.addAttribute("message","product is not existed");
		 return new ModelAndView( "forward:/admin/products",model);
	}
	
	
	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> saveFile (@PathVariable String filename){
		
		Resource file = storageService.loadAsResource(filename);
		return  ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, 
				"attachment; filename=\"" + file.getFilename() +"\"").body(file);
	}
	
	@GetMapping("delete/{productId}")
	public ModelAndView  delete(ModelMap model , @PathVariable ("productId") Long productId) throws IOException{
		Optional<Product> opt = productService.findById(productId);
		
		 if (opt.isPresent()) {
			if(!StringUtils.isEmpty(opt.get().getImage())) {
				storageService.delete(opt.get().getImage());
			}
			productService.deleteById(productId);
			model.addAttribute("message" , "Product is delete");
		}else {
			model.addAttribute("message" , "Product is not found");
		}
		
		return new ModelAndView("forward:/admin/products",model);	
	}
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model ,
			@Valid @ModelAttribute ("product") ProductDto dto , BindingResult result) {
		if(result.hasErrors()){
			return new ModelAndView("admin/products/addOrEdit");
		}
		Product entity = new Product();
		BeanUtils.copyProperties(dto, entity);
		
		Category category = new Category();
		category.setCategoryId(dto.getCategoryId());
		entity.setCategory(category);
		if(!dto.getImageFile().isEmpty()) {
			UUID uuid = UUID.randomUUID();
			String uuString = uuid.toString();
			entity.setImage(storageService.getstoragedFileName(dto.getImageFile(), uuString));
			storageService.store(dto.getImageFile(), entity.getImage());
		}
		
		productService.save(entity);
		model.addAttribute("message" , "product is save !");
		return new ModelAndView( "forward:/admin/products",model);
	}
	
	@RequestMapping("")
	public String  list(ModelMap model) {
		List<Product> list = productService.findAll();
		model.addAttribute("products", list);
		return "admin/products/list";
		
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
		model.addAttribute("products", list);
		return "admin/products/search";
	}
	

}
