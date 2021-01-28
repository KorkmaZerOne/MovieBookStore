package com.omerkorkmaz.moviboostore.validators;

import com.omerkorkmaz.moviboostore.model.Category;
import com.omerkorkmaz.moviboostore.services.CategoryService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CategoryValidator implements Validator
{
	protected CategoryService categoryService;
	
	@Override
	public boolean supports(Class<?> clazz)
	{
		return Category.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors)
	{
		Category category = (Category) target;
		String name = category.getName();
		Category categoryByName = categoryService.getCategoryByName(name);
		if(categoryByName != null){
			errors.rejectValue("name", "error.exists", new Object[]{name}, "Category "+category.getName()+" already exists");
		}
	}
	
	
}
