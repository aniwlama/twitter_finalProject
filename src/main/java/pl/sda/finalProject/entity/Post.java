package pl.sda.finalProject.entity;


import lombok.Data;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    private LocalDateTime createDate;
    private String text;
    private LocalDateTime modifyDate;
    private LocalDateTime deleteDate;
    private int userId;
    private int commentId;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "user_id")
    private User user;

/*    @OneToMany
    @JoinColumn(name = "comment_id")*/



}
