package pl.sda.finalProject.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Data
public class Comment {

    //dodac anotacje do column name
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;
    private LocalDateTime createDate;
    private String text;
    private LocalDateTime modifyDate;
    private LocalDateTime deleteDate;
    private int userId;
    private int postId;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "post_id")
    private Post post;





}
