package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Material;

public class MaterialEditor extends PropertyEditorSupport{

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(Material.valueOf(text));
	}
}
