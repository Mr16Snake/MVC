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

import ua.entity.Budget;
import ua.form.filter.BudgetFilterForm;
import ua.service.BudgetService;
import ua.service.implementation.validator.BudgetValidator;

@Controller
public class BudgetController {

	@Autowired
	private BudgetService budgetService;
	
	@ModelAttribute("budget")
	public Budget getBudget(){
		return new Budget();
	}
	
	@InitBinder("budget")
	protected void initBinder(WebDataBinder binder){
	   binder.setValidator(new BudgetValidator(budgetService));
	}
	
	@RequestMapping("/admin/budget")
	public String show(Model model, @PageableDefault(5) Pageable pageable, @ModelAttribute(value="filter") BudgetFilterForm form){
		model.addAttribute("page", budgetService.findAll(pageable, form));
		return "adminBudget";
	}
	
	@RequestMapping("/admin/budget/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault(5) Pageable pageable, @ModelAttribute(value="filter") BudgetFilterForm form){
		budgetService.delete(id);
		return "redirect:/admin/budget"+getParams(pageable, form);
	}
	
	@RequestMapping("/admin/budget/update/{id}")
	public String update(Model model, @PathVariable int id, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") BudgetFilterForm form){
		model.addAttribute("budget", budgetService.findOne(id));
		model.addAttribute("page", budgetService.findAll(pageable, form));
		return "adminBudget";
	}
	
	@RequestMapping(value = "/admin/budget", method=RequestMethod.POST)
	public String save(@ModelAttribute("budget") @Valid Budget budget, BindingResult br,	@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") BudgetFilterForm form,	Model model){
		if(br.hasErrors()){
			model.addAttribute("page", budgetService.findAll(pageable, form));
			return "adminBudget";
		}
		budgetService.save(budget);
		return "redirect:/admin/budget"+getParams(pageable, form);
	}
	
	private String getParams(Pageable pageable, BudgetFilterForm form){
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
