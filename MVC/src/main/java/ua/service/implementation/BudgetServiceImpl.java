package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.Budget;
import ua.form.filter.BudgetFilterForm;
import ua.repository.BudgetRepository;
import ua.service.BudgetService;
import ua.service.implementation.specification.BudgetFilterAdapter;

@Service
public class BudgetServiceImpl implements BudgetService{
	
	@Autowired
	private BudgetRepository budgetRepository;

	@Override
	public void save(Budget budget) {
		budgetRepository.save(budget);
	}

	@Override
	public void delete(int id) {
		budgetRepository.delete(id);
	}

	@Override
	public List<Budget> findAll() {
		return budgetRepository.findAll();
	}
	
	@Override
	public Budget findOne(int id) {
		return budgetRepository.findOne(id);
	}

	@Override
	public Budget findByValue(String value) {
		return budgetRepository.findByValue(value);
	}

	@Override
	public Page<Budget> findAll(Pageable pageable, BudgetFilterForm form) {
		return budgetRepository.findAll(new BudgetFilterAdapter(form), pageable);
	}

}
