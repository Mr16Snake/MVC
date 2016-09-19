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
import ua.entity.Budget;
import ua.entity.Material;
import ua.entity.ScreenSize;
import ua.entity.Smartphone;
import ua.form.filter.SmartphoneFilterForm;
import ua.service.BrandService;
import ua.service.BudgetService;
import ua.service.ScreenSizeService;
import ua.service.SmartphoneService;
import ua.service.implementation.editor.BrandEditor;
import ua.service.implementation.editor.BudgetEditor;
import ua.service.implementation.editor.MaterialEditor;
import ua.service.implementation.editor.ScreenSizeEditor;
import ua.service.implementation.validator.SmartphoneValidator;

@Controller
public class SmartphoneController {

	@Autowired
	private SmartphoneService smartService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private ScreenSizeService screenService;
	
	@Autowired
	private BudgetService budgetService;
	
	@InitBinder("form")
	protected void initBinder(WebDataBinder binder){
	   binder.registerCustomEditor(Brand.class, new BrandEditor(brandService));
	   binder.registerCustomEditor(Budget.class, new BudgetEditor(budgetService));
	   binder.registerCustomEditor(ScreenSize.class, new ScreenSizeEditor(screenService));
	   binder.registerCustomEditor(Material.class, new MaterialEditor());
	   binder.setValidator(new SmartphoneValidator(smartService));
	}
	
	@ModelAttribute("form")
	public Smartphone getForm(){
		return new Smartphone();
	}
	
	@ModelAttribute("filter")
	public SmartphoneFilterForm getFilter(){
		return new SmartphoneFilterForm();
	}
	
	@RequestMapping("/admin/smartphone")
	public String showSmartphones(Model model,	@PageableDefault(5) Pageable pageable,	@ModelAttribute(value="filter") SmartphoneFilterForm form){
		model.addAttribute("page", smartService.findAll(pageable, form));
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("budgets", budgetService.findAll());
		model.addAttribute("screens", screenService.findAll());
		return "adminSmartphone";
	}
	
	@RequestMapping(value="/admin/smartphone/update/{id}")
	public String update(Model model, @PathVariable int id, @PageableDefault(5) Pageable pageable, @ModelAttribute(value="filter") SmartphoneFilterForm form){
		model.addAttribute("form", smartService.findOne(id));
		model.addAttribute("page", smartService.findAll(pageable, form));
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("budgets", budgetService.findAll());
		model.addAttribute("screens", screenService.findAll());
		return "adminSmartphone";
	}
	
	@RequestMapping("/admin/smartphone/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault(5) Pageable pageable, @ModelAttribute(value="filter") SmartphoneFilterForm form){
		smartService.delete(id);
		return "redirect:/admin/smartphone"+getParams(pageable, form);
	}
	
	@RequestMapping(value = "/admin/smartphone", method=RequestMethod.POST)
	public String save(@ModelAttribute("form") @Valid Smartphone smartphone, BindingResult br, Model model,	@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") SmartphoneFilterForm filter){
		if(br.hasErrors()){
			model.addAttribute("page", smartService.findAll(pageable, filter));
			model.addAttribute("brands", brandService.findAll());
			model.addAttribute("budgets", budgetService.findAll());
			model.addAttribute("screens", screenService.findAll());
			return "adminSmartphone";
		}
		smartService.save(smartphone);
		return "redirect:/admin/smartphone"+getParams(pageable, filter);
	}
	
	private String getParams(Pageable pageable, SmartphoneFilterForm form){
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
		return buffer.toString();
	}
}
