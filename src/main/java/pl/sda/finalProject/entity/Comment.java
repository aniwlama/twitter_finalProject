package pl.sda.finalProject.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
public class Comment {

    //dodac login? zeby wyswietlalo kto napisal post/komentarz
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private int commentId;
    @Column(name="create_date")
    private LocalDateTime createDate;
    private String text;
    @Column(name="modify_date")
    private LocalDateTime modifyDate;
    @Column(name="delete_date")
    private LocalDateTime deleteDate;
    @Column(name="user_id")
    private int userId;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "post_id")
    private Post post;




}
