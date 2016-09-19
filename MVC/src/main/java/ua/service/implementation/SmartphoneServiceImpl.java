package ua.service.implementation;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.Smartphone;
import ua.form.SmartphoneForm;
import ua.form.filter.SmartphoneFilterForm;
import ua.repository.SmartphoneRepository;
import ua.service.BrandService;
import ua.service.BudgetService;
import ua.service.FileWriter;
import ua.service.FileWriter.Folder;
import ua.service.ScreenSizeService;
import ua.service.SmartphoneService;

@Service
public class SmartphoneServiceImpl implements SmartphoneService{

	@Resource
	private SmartphoneRepository smartphoneRepository;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private BudgetService budgetService;
	
	@Autowired
	private ScreenSizeService screenService;
	
	@Autowired
	private FileWriter fileWriter;
	
	@Override
	public void save(SmartphoneForm form) {
		Smartphone smart = new Smartphone();
		smart.setId(form.getId());
		smart.setBrand(form.getBrand());
		smart.setModel(form.getModel());
		smart.setBatteryGood(form.isBatteryGood());
		smart.setBudget(form.getBudget());
		smart.setMaterial(form.getMaterial());
		smart.setFrontCamera(form.isFrontCamera());
		smart.setMainCamera(form.isMainCamera());
		smart.setPowerful(form.isPowerful());
		smart.setDescription(form.getDescription());
		smart.setUrl(form.getUrl());
		smart.setScreenSize(form.getScreenSize());
		smart.setPath(form.getPath());
		smart.setVersion(form.getVersion());
		smartphoneRepository.saveAndFlush(smart);
		String extension = fileWriter.write(Folder.SMARTPHONE, form.getFile(), smart.getId());
		if(extension!=null){
			smart.setVersion(form.getVersion()+1);
			smart.setPath(extension);
			smartphoneRepository.save(smart);
		}
	}
	
	@Override
	public SmartphoneForm findForForm(int id) {
		Smartphone smart = smartphoneRepository.findOneBrandBudgetScreenSizeInited(id);
		SmartphoneForm form = new SmartphoneForm();
		form.setId(smart.getId());
		form.setBrand(smart.getBrand());
		form.setModel(smart.getModel());
		form.setBatteryGood(smart.isBatteryGood());
		form.setBudget(smart.getBudget());
		form.setMaterial(smart.getMaterial());
		form.setFrontCamera(smart.isFrontCamera());
		form.setMainCamera(smart.isMainCamera());
		form.setPowerful(form.isPowerful());
		form.setDescription(form.getDescription());
		form.setUrl(form.getUrl());
		form.setScreenSize(form.getScreenSize());
		form.setPath(smart.getPath());
		form.setVersion(smart.getVersion());
		return form;
	}

	@Override
	public void delete(int id) {
		smartphoneRepository.delete(id);
	}

	@Override
	public List<Smartphone> findAll() {
		return smartphoneRepository.findAll();
	}
	
	public Smartphone findOne(int id){
		return smartphoneRepository.findOneBrandBudgetScreenSizeInited(id);
	}

	@Override
	public Smartphone findByModel(Smartphone smartphone) {
		return smartphoneRepository.findByModel(smartphone.getModel());
	}
	
	@Override
	public Page<Smartphone> findAll(Pageable pageable) {
		return smartphoneRepository.findAll(pageable);
	}

	@Override
	public Page<Smartphone> findAll(Pageable pageable, SmartphoneFilterForm form) {
		return smartphoneRepository.findAll(pageable);
	}

	@Override
	public void save(Smartphone smartphone) {
		smartphoneRepository.save(smartphone);
	}
}
