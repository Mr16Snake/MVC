package ua.service.implementation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.Budget;
import ua.service.BudgetService;

public class BudgetValidator implements Validator{

	private BudgetService budgetService;
	
	public BudgetValidator(BudgetService budgetService) {
		this.budgetService = budgetService;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Budget.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Budget budget = (Budget) target;
		if(budget.getId()==0)if(budgetService.findByValue(budget.getValue())!=null){
			errors.rejectValue("value", "", "*** This budget already exists ***");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "value", "", "*** Can`t be empty ***");
	}

}
