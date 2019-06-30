package pl.sda.finalProject.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.finalProject.entity.Post;
import pl.sda.finalProject.exceptions.UnsupportedExceptions;
import pl.sda.finalProject.model.PostDto;
import pl.sda.finalProject.model.UserDto;
import pl.sda.finalProject.repository.PostRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PostService {

    private PostRepository postRepository;
    private ModelMapper modelMapper;
    private UserService userService;

    @Autowired
    public PostService(PostRepository postRepository, ModelMapper modelMapper, UserService userService) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    private Date dateFormatter() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newDateFormat = sdf.format(new Date());
        Date dateFormatted;

        try{
             dateFormatted = sdf.parse(newDateFormat);
        } catch(ParseException e){
            e.printStackTrace();
            throw new UnsupportedExceptions(e.getMessage());
        }

        return dateFormatted;

    }

    public void savePost(PostDto postDto){
        postDto.setCreateDate(dateFormatter());
        Post postToSave = modelMapper.map(postDto, Post.class);
        /*ustawienie aktywnego użytkownika*/
        postToSave.setUser(userService.getActiveUser());
        postRepository.save(postToSave);

    }





}
