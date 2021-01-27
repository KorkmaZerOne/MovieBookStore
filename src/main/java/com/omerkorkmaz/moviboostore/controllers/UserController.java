package com.omerkorkmaz.moviboostore.controllers;

import com.omerkorkmaz.moviboostore.model.User;
import com.omerkorkmaz.moviboostore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String userSignUp(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "signup";
        }
        userService.createUser(user);
        return "redirect:signin";
    }

    @GetMapping("signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());

        return "signup";
    }

    @GetMapping("/signin")
    public String signin(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {

        return "signin";
    }

    @PostMapping("/signin")
    public String validateUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
        User userData = userService.getUserByEmailAndPassword(user.getEmail(),user.getPassword());
        if(userData==null)
            return "signin";

        return "redirect:index";
    }

}
