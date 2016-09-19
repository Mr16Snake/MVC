package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Brand;
import ua.form.filter.BrandFilterForm;

public interface BrandService {

	public void save(Brand brand);
	public void delete(int id);
	public void delete(String name);
	public List<Brand> findAll();
	public Brand findOne(int id);
	public Page<Brand> findAll(Pageable pageable, BrandFilterForm form);
	public Page<Brand> findAll(Pageable pageable);
	public List<Brand> findWithSmartphones();
	public Brand findByName(String name);
}
