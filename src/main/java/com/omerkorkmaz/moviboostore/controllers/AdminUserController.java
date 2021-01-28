package com.omerkorkmaz.moviboostore.controllers;

import com.omerkorkmaz.moviboostore.model.Role;
import com.omerkorkmaz.moviboostore.model.User;
import com.omerkorkmaz.moviboostore.services.SecurityService;
import com.omerkorkmaz.moviboostore.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminUserController {

    @Autowired
    protected SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @ModelAttribute("rolesList")
    public List<Role> rolesList(){
        return securityService.getAllRoles();
    }

    @RequestMapping(value="/users", method= RequestMethod.GET)
    public String listUsers(Model model) {
        List<User> list = securityService.getAllUsers();
        model.addAttribute("users",list);
        return "users/users";
    }

    @RequestMapping(value="/users/new", method=RequestMethod.GET)
    public String createUserForm(Model model) {
        User user = new User();
        model.addAttribute("user",user);
        model.addAttribute("rolesList",securityService.getAllRoles());

        return "users/create_user";
    }

    @RequestMapping(value="/users", method=RequestMethod.POST)
    public String createUser(@Valid @ModelAttribute("user") User user, BindingResult result,
                             Model model, RedirectAttributes redirectAttributes) {
        userValidator.validate(user, result);
        if(result.hasErrors()){
            return "users/create_user";
        }
        String password = user.getPassword();
        user.setPassword(password);
        User persistedUser = securityService.createUser(user);
        redirectAttributes.addFlashAttribute("info", "User created successfully");
        return "redirect:users/users";
    }

    @RequestMapping(value="/users/{id}", method=RequestMethod.GET)
    public String editUserForm(@PathVariable Integer id, Model model) {
        User user = securityService.getUserById(id);
        Map<Integer, Role> assignedRoleMap = new HashMap<>();
        List<Role> roles = user.getRoles();
        for (Role role : roles)
        {
            assignedRoleMap.put(role.getUserRoleId(), role);
        }
        List<Role> userRoles = new ArrayList<>();
        List<Role> allRoles = securityService.getAllRoles();
        for (Role role : allRoles)
        {
            if(assignedRoleMap.containsKey(role.getUserRoleId())){
                userRoles.add(role);
            } else {
                userRoles.add(null);
            }
        }
        user.setRoles(userRoles);
        model.addAttribute("user",user);
        model.addAttribute("rolesList",allRoles);
        return "users/edit_user";
    }

    @RequestMapping(value="/users/{id}", method=RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user, BindingResult result,
                             Model model, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()){
            return "users/edit_user";
        }
        User persistedUser = securityService.updateUser(user);
        redirectAttributes.addFlashAttribute("info", "User updates successfully");
        return "redirect:users/users";
    }
}
