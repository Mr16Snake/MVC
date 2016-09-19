package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.Brand;
import ua.form.filter.BrandFilterForm;
import ua.repository.BrandRepository;
import ua.service.BrandService;
import ua.service.implementation.specification.BrandFilterAdapter;

@Service
public class BrandServiceImpl implements BrandService{
	
	@Autowired
	private BrandRepository brandRepository;

	@Override
	public void save(Brand brand) {
		brandRepository.save(brand);
	}

	@Override
	public void delete(int id) {
		brandRepository.delete(id);
	}
	
	@Override
	public void delete(String name) {
		brandRepository.delete(name);
	}

	@Override
	public List<Brand> findAll() {
		return brandRepository.findAll();
	}

	@Override
	public Brand findOne(int id) {
		return brandRepository.findOne(id);
	}
	
	@Override
	public Page<Brand> findAll(Pageable pageable) {
		return brandRepository.findAll(pageable);
	}

	@Override
	public Page<Brand> findAll(Pageable pageable, BrandFilterForm form) {
		return brandRepository.findAll(new BrandFilterAdapter(form), pageable);
	}

	@Override
	public List<Brand> findWithSmartphones() {
		return brandRepository.findWithSmartphones();
	}

	@Override
	public Brand findByName(String name) {
		return brandRepository.findByName(name);
	}

}
