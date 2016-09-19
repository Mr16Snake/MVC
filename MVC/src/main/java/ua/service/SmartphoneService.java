package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Smartphone;
import ua.form.SmartphoneForm;
import ua.form.filter.SmartphoneFilterForm;

public interface SmartphoneService {

	public void save(Smartphone smartphone);
	public void save(SmartphoneForm form);
	public SmartphoneForm findForForm(int id);
	public void delete(int id);
	public List<Smartphone> findAll();
	public Smartphone findOne(int id);
	public Smartphone findByModel(Smartphone smartphone);
	public Page<Smartphone> findAll(Pageable pageable);
	public Page<Smartphone> findAll(Pageable pageable, SmartphoneFilterForm form);
}
