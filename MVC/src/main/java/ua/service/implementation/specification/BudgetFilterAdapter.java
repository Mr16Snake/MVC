package ua.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.Budget;
import ua.form.filter.BudgetFilterForm;

public class BudgetFilterAdapter implements Specification<Budget>{

	private String search = "";

	public BudgetFilterAdapter(BudgetFilterForm form) {
		search = form.getSearch();
	}

	@Override
	public Predicate toPredicate(Root<Budget> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if (query.getResultType() != Long.class && query.getResultType() != long.class) {
//			ÿךרמ ענובא רמסü פועקטעט
		}
		Expression<String> exp = root.get("value");
		return cb.like(cb.upper(exp), search.toUpperCase()+"%");
	}
	
	
}

