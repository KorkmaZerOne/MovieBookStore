package com.omerkorkmaz.moviboostore.validators;

import com.omerkorkmaz.moviboostore.model.User;
import com.omerkorkmaz.moviboostore.services.SecurityService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator
{
	protected SecurityService securityService;
	
	@Override
	public boolean supports(Class<?> clazz)
	{
		return User.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors)
	{
		User user = (User) target;
		String email = user.getEmail();
		User userByEmail = securityService.findUserByEmail(email);
		if(userByEmail != null){
			errors.rejectValue("email", "error.exists", new Object[]{email}, "Email "+email+" already in use");
		}
	}
	
	
}
