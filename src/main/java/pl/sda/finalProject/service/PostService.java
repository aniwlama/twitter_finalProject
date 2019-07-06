package pl.sda.finalProject.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.finalProject.entity.Post;
import pl.sda.finalProject.exceptions.UnsupportedExceptions;
import pl.sda.finalProject.model.PostDto;
import pl.sda.finalProject.repository.PostRepository;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

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

        try {
            dateFormatted = sdf.parse(newDateFormat);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new UnsupportedExceptions(e.getMessage());
        }

        return dateFormatted;

    }

    public void savePost(PostDto postDto) {
        postDto.setCreateDate(dateFormatter());
        Post postCreate = modelMapper.map(postDto, Post.class);
        /*ustawienie aktywnego użytkownika*/
        postCreate.setUser(userService.getActiveUser());
        postRepository.save(postCreate);

    }

    public List<PostDto> getAllPosts() {

        List<Post> posts = postRepository.findAll();

        return posts.stream()
                .sorted((id1, id2)->Long.compare(id2.getPostId(),id1.getPostId()))
                .map(p -> modelMapper.map(p, PostDto.class))
                .collect(Collectors.toList());

    }

    public void removePost(PostDto postDto) {


        Post postToDelete = postRepository
                .findPostByPostId(postDto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found!"));
        postToDelete.setDeleteDate(dateFormatter());

        String userCurrentlyLogged = userService.getActiveUser().getLogin();
        String postUser = postToDelete.getUser().getLogin();

        if(userCurrentlyLogged.equals(postUser)){
            postRepository.save(postToDelete);
        } else {
            throw new RuntimeException("You can't delete someone's else post!");
        }

    }


}






