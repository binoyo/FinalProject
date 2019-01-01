package com.lambton.project.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.lambton.project.commands.ItemForm;
import com.lambton.project.entity.Item;

@Component
public class ItemValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return Item.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ItemForm item = (ItemForm) o;

		ValidationUtils.rejectIfEmpty(errors, "name", "NotEmpty");
		if (item.getName().length() > 255) {
			errors.rejectValue("name", "Size.itemForm.name");
		}

		ValidationUtils.rejectIfEmpty(errors, "description", "NotEmpty");
		if (item.getName().length() > 255) {
			errors.rejectValue("description", "Size.itemForm.name");
		}

		ValidationUtils.rejectIfEmpty(errors, "price", "NotEmpty");

		ValidationUtils.rejectIfEmpty(errors, "imageUrl", "NotEmpty");
		if (item.getName().length() > 255) {
			errors.rejectValue("imageUrl", "Size.itemForm.name");
		}
	}
}
