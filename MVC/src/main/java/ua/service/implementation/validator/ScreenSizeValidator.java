package ua.service.implementation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.ScreenSize;
import ua.service.ScreenSizeService;

public class ScreenSizeValidator implements Validator{

	private ScreenSizeService screenService;
	
	public ScreenSizeValidator(ScreenSizeService screenService) {
		this.screenService = screenService;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ScreenSize.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ScreenSize screen = (ScreenSize) target;
		if(screen.getId()==0)if(screenService.findByValue(screen.getValue())!=null){
			errors.rejectValue("value", "", "*** This screen size already exists ***");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "value", "", "*** Can`t be empty ***");
	}

}
