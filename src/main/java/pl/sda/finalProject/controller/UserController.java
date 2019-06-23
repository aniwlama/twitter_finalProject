package pl.sda.finalProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.finalProject.model.UserDto;
import pl.sda.finalProject.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/registeruser")
    public ModelAndView registerUser(){
        return new ModelAndView("registerform", "userToRegister", new UserDto());
    }

    @PostMapping("/registeruser")
    public String registerUser(@ModelAttribute UserDto userDto){

        userService.saveUser(userDto);
        return "userRegisterSuccess";
    }
}