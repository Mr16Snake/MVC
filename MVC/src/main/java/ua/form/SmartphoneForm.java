package ua.form;

import org.springframework.web.multipart.MultipartFile;

import ua.entity.Brand;
import ua.entity.Budget;
import ua.entity.Material;
import ua.entity.ScreenSize;

public class SmartphoneForm {

private Brand brand;

	private int id;
	
	private String model;
	
	private Material material;
	
	private boolean powerful;
	
	private ScreenSize screenSize;
	
	private boolean frontCamera;
	
	private boolean mainCamera;
	
	private boolean batteryGood;
	
	private Budget budget;
	
	private String url;
	
	private String description;
	
	private String path;
	
	private int version;
	
	private MultipartFile file;

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public boolean isPowerful() {
		return powerful;
	}

	public void setPowerful(boolean powerful) {
		this.powerful = powerful;
	}

	public ScreenSize getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(ScreenSize screenSize) {
		this.screenSize = screenSize;
	}

	public boolean isFrontCamera() {
		return frontCamera;
	}

	public void setFrontCamera(boolean frontCamera) {
		this.frontCamera = frontCamera;
	}

	public boolean isMainCamera() {
		return mainCamera;
	}

	public void setMainCamera(boolean mainCamera) {
		this.mainCamera = mainCamera;
	}

	public boolean isBatteryGood() {
		return batteryGood;
	}

	public void setBatteryGood(boolean batteryGood) {
		this.batteryGood = batteryGood;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
