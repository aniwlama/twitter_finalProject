package pl.sda.finalProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.finalProject.model.UserDto;


@Controller
public class RegisterFormController {

    @RequestMapping("/registerform")
    public String registerform(Model model, Model model2){
        model.addAttribute("userToRegister", new UserDto());
        return "registerform";
    }

}
