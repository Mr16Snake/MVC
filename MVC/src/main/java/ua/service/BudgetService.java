package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Budget;
import ua.form.filter.BudgetFilterForm;

public interface BudgetService {

	public void save(Budget budget);
	public void delete(int id);
	public List<Budget> findAll();
	public Page<Budget> findAll(Pageable pageable, BudgetFilterForm form);
	public Budget findOne(int id);
	public Budget findByValue(String value);
}
