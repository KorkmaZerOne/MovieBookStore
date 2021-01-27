package com.omerkorkmaz.moviboostore.validators;

import com.omerkorkmaz.moviboostore.model.Category;
import com.omerkorkmaz.moviboostore.services.ProductService;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CategoryValidator implements Validator
{
	protected ProductService productService;
	
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
		Category categoryByName = productService.getCategoryByName(name);
		if(categoryByName != null){
			errors.rejectValue("name", "error.exists", new Object[]{name}, "Category "+category.getName()+" already exists");
		}
	}
	
	
}
