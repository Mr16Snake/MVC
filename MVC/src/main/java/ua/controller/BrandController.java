package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.entity.Brand;
import ua.form.filter.BrandFilterForm;
import ua.service.BrandService;
import ua.service.implementation.validator.BrandValidator;

@Controller
public class BrandController {

	@Autowired
	private BrandService brandService;
	
	@ModelAttribute("brand")
	public Brand getBrand(){
		return new Brand();
	}
	
	@ModelAttribute("filter")
	public BrandFilterForm getFilter(){
		return new BrandFilterForm();
	}
	
	@InitBinder("brand")
	protected void initBinder(WebDataBinder binder){
	   binder.setValidator(new BrandValidator(brandService));
	}
	
	@RequestMapping("/admin/brand")
	public String show(Model model, @PageableDefault(5) Pageable pageable, @ModelAttribute(value="filter") BrandFilterForm form){
		model.addAttribute("page", brandService.findAll(pageable, form));
		return "adminBrand";
	}
	
	@RequestMapping("/admin/brand/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault(5) Pageable pageable, @ModelAttribute(value="filter") BrandFilterForm form){
		brandService.delete(id);
		return "redirect:/admin/brand"+getParams(pageable, form);
	}
	
	@RequestMapping("/admin/brand/update/{id}")
	public String update(Model model, @PathVariable int id, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") BrandFilterForm form){
		model.addAttribute("brand", brandService.findOne(id));
		model.addAttribute("page", brandService.findAll(pageable, form));
		return "adminBrand";
	}
	
	@RequestMapping(value = "/admin/brand", method=RequestMethod.POST)
	public String save(@ModelAttribute("brand") @Valid Brand brand, BindingResult br,	@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") BrandFilterForm form,	Model model){
		if(br.hasErrors()){
			model.addAttribute("page", brandService.findAll(pageable, form));
			return "adminBrand";
		}
		brandService.save(brand);
		return "redirect:/admin/brand"+getParams(pageable, form);
	}
	
	@RequestMapping("/admin/brand/smartphone")
	public String showBrand(Model model){
		model.addAttribute("brands", brandService.findWithSmartphones());
		return "adminBrandSmartphone";
	}
	
	private String getParams(Pageable pageable, BrandFilterForm form){
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=");
		buffer.append(String.valueOf(pageable.getPageNumber()+1));
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if(pageable.getSort()!=null){
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order)->{
				buffer.append(order.getProperty());
				if(order.getDirection()!=Direction.ASC)
				buffer.append(",desc");
			});
		}
		buffer.append("&search=");
		buffer.append(form.getSearch());
		return buffer.toString();
	}
}
