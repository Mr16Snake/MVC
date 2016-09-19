package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.ScreenSize;
import ua.form.filter.ScreenSizeFilterForm;
import ua.repository.ScreenSizeRepository;
import ua.service.ScreenSizeService;
import ua.service.implementation.specification.ScreenSizeFilterAdapter;

@Service
public class ScreenSizeServiceImpl implements ScreenSizeService{
	
	@Autowired
	private ScreenSizeRepository screenSizeRepository;

	@Override
	public void save(ScreenSize screenSize) {
		screenSizeRepository.save(screenSize);
	}

	@Override
	public void delete(int id) {
		screenSizeRepository.delete(id);
	}

	@Override
	public List<ScreenSize> findAll() {
		return screenSizeRepository.findAll();
	}
	
	@Override
	public ScreenSize findOne(int id) {
		return screenSizeRepository.findOne(id);
	}

	@Override
	public ScreenSize findByValue(String value) {
		return screenSizeRepository.findByValue(value);
	}

	@Override
	public Page<ScreenSize> findAll(Pageable pageable, ScreenSizeFilterForm form) {
		return screenSizeRepository.findAll(new ScreenSizeFilterAdapter(form), pageable);
	}

}
