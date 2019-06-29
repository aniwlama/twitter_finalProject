package pl.sda.finalProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorLogin {

    @GetMapping(value="/errorLogin")
    public String errorLogin(){
        return "errorLogin";
    }
}
