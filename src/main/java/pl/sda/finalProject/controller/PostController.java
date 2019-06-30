package pl.sda.finalProject.controller;

import javafx.geometry.Pos;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.finalProject.entity.Post;
import pl.sda.finalProject.model.PostDto;
import pl.sda.finalProject.service.PostService;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = {"/", "/index"})
    public String addPost(Model model){
        model.addAttribute("postToSave", new PostDto());
        return "index";
    }

    @GetMapping("/addpost")
    public ModelAndView savePost(){
        return new ModelAndView("index","postToSave", new PostDto());
    }

    @PostMapping("/addpost")
    public String savePost(@ModelAttribute PostDto postDto){
        postService.savePost(postDto);
        return "postAddedSuccess";
    }


}
