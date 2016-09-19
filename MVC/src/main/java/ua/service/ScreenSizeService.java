package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.ScreenSize;
import ua.form.filter.ScreenSizeFilterForm;

public interface ScreenSizeService {

	public void save(ScreenSize screenSize);
	public void delete(int id);
	public List<ScreenSize> findAll();
	public ScreenSize findOne(int id);
	ScreenSize findByValue(String value);
	public Page<ScreenSize> findAll(Pageable pageable, ScreenSizeFilterForm form);
}
