package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Budget;
import ua.service.BudgetService;

public class BudgetEditor extends PropertyEditorSupport{

	private final BudgetService budgetService;

	public BudgetEditor(BudgetService budgetService) {
		this.budgetService = budgetService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Budget budget = budgetService.findOne(Integer.valueOf(text));
		setValue(budget);
	}
}
