package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.entity.Budget;

public interface BudgetRepository extends JpaRepository<Budget, Integer>, JpaSpecificationExecutor<Budget>{
	
	public Budget findByValue(String value);
}
