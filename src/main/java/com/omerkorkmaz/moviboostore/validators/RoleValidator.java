package com.omerkorkmaz.moviboostore.validators;

import com.omerkorkmaz.moviboostore.model.Role;
import com.omerkorkmaz.moviboostore.services.SecurityService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RoleValidator implements Validator
{

	protected SecurityService securityService;
	
	@Override
	public boolean supports(Class<?> clazz)
	{
		return Role.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors)
	{
		Role role = (Role) target;
		String name = role.getName();
		Role RoleByName = securityService.getRoleByName(name);
		if(RoleByName != null){
			errors.rejectValue("name", "error.exists",new Object[]{name}, "Role "+name+" already exists");
		}
	}
	
	
}
