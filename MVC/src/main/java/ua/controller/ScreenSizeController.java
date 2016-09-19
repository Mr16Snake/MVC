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

import ua.entity.ScreenSize;
import ua.form.filter.ScreenSizeFilterForm;
import ua.service.ScreenSizeService;
import ua.service.implementation.validator.ScreenSizeValidator;

@Controller
public class ScreenSizeController {

	@Autowired
	private ScreenSizeService screenService;
	
	@ModelAttribute("screenSize")
	public ScreenSize getScreenSize(){
		return new ScreenSize();
	}
	
	@InitBinder("screenSize")
	protected void initBinder(WebDataBinder binder){
	   binder.setValidator(new ScreenSizeValidator(screenService));
	}
	
	@RequestMapping("/admin/screensize")
	public String show(Model model, @PageableDefault(5) Pageable pageable, @ModelAttribute(value="filter") ScreenSizeFilterForm form){
		model.addAttribute("page", screenService.findAll(pageable, form));
		return "adminScreenSize";
	}
	
	@RequestMapping("/admin/screensize/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault(5) Pageable pageable, @ModelAttribute(value="filter") ScreenSizeFilterForm form){
		screenService.delete(id);
		return "redirect:/admin/screensize"+getParams(pageable, form);
	}
	
	@RequestMapping("/admin/screensize/update/{id}")
	public String update(Model model, @PathVariable int id, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") ScreenSizeFilterForm form){
		model.addAttribute("screenSize", screenService.findOne(id));
		model.addAttribute("page", screenService.findAll(pageable, form));
		return "adminScreenSize";
	}
	
	@RequestMapping(value = "/admin/screensize", method=RequestMethod.POST)
	public String save(@ModelAttribute("screenSize") @Valid ScreenSize screenSize, BindingResult br,	@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") ScreenSizeFilterForm form,	Model model){
		if(br.hasErrors()){
			model.addAttribute("page", screenService.findAll(pageable, form));
			return "adminScreenSize";
		}
		screenService.save(screenSize);
		return "redirect:/admin/screensize"+getParams(pageable, form);
	}
	
	private String getParams(Pageable pageable, ScreenSizeFilterForm form){
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
