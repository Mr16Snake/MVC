package ua.service.implementation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.Smartphone;
import ua.service.SmartphoneService;

public class SmartphoneValidator implements Validator{

	private SmartphoneService smartService;
	
	public SmartphoneValidator(SmartphoneService smartService) {
		this.smartService = smartService;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Smartphone.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Smartphone smartphone = (Smartphone) target;
		if(smartphone.getId()==0)if(smartService.findByModel(smartphone)!=null){
			errors.rejectValue("model", "", "*** This smartphone already exists ***");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "model", "", "*** Can`t be empty ***");
	}

}
