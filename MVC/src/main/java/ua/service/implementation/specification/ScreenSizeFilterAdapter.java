package ua.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.ScreenSize;
import ua.form.filter.ScreenSizeFilterForm;


public class ScreenSizeFilterAdapter implements Specification<ScreenSize>{

	private String search = "";

	public ScreenSizeFilterAdapter(ScreenSizeFilterForm form) {
		search = form.getSearch();
	}

	@Override
	public Predicate toPredicate(Root<ScreenSize> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if (query.getResultType() != Long.class && query.getResultType() != long.class) {
//			ÿךרמ ענובא רמסü פועקטעט
		}
		Expression<String> exp = root.get("value");
		return cb.like(cb.upper(exp), search.toUpperCase()+"%");
	}
	
	
}

