package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.ScreenSize;
import ua.service.ScreenSizeService;

public class ScreenSizeEditor extends PropertyEditorSupport{

	private final ScreenSizeService screenSizeService;

	public ScreenSizeEditor(ScreenSizeService screenSizeService) {
		this.screenSizeService = screenSizeService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		ScreenSize screenSize = screenSizeService.findOne(Integer.valueOf(text));
		setValue(screenSize);
	}
}