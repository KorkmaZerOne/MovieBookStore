package com.omerkorkmaz.moviboostore.controllers;

import com.omerkorkmaz.moviboostore.model.Role;
import com.omerkorkmaz.moviboostore.model.User;
import com.omerkorkmaz.moviboostore.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AdminUserController {
    private static final String viewPrefix = "users/";
    @Autowired
    protected SecurityService securityService;

    @ModelAttribute("rolesList")
    public List<Role> rolesList(){
        return securityService.getAllRoles();
    }

    @RequestMapping(value="/users", method= RequestMethod.GET)
    public String listUsers(Model model) {
        List<User> list = securityService.getAllUsers();
        model.addAttribute("users",list);
        return viewPrefix+"users";
    }
}
