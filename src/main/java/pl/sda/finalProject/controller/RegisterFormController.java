package pl.sda.finalProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.finalProject.model.UserDto;
import pl.sda.finalProject.service.UserService;

import javax.validation.Valid;
import java.text.ParseException;


@Controller
public class RegisterFormController {

    @Autowired
    private UserService userService;

    @GetMapping("/registeruser")
    public ModelAndView registerUser(){
        return new ModelAndView("registerform", "userToRegister", new UserDto());
    }

    @PostMapping("/registeruser")
    public String registerUser(@ModelAttribute @Valid UserDto userDto, BindingResult bindingResult) throws ParseException {
        userService.saveUser(userDto,bindingResult);
        return "userRegisterSuccess";
    }

    @RequestMapping("/registerform")
    public String registerform(Model model) {
        model.addAttribute("userToRegister", new UserDto());
        return "registerform";
    }

}
