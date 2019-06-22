package pl.sda.finalProject.entity;


import lombok.Data;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private int postId;
    @Column(name="create_date")
    private LocalDateTime createDate;
    private String text;
    @Column(name="modify_date")
    private LocalDateTime modifyDate;
    @Column(name="delete_date")
    private LocalDateTime deleteDate;
    //@Column(name="user_id")
    private int userId;
    //@Column(name="comment_id")
    private int commentId;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "user_id")
    private User user;

/*    @OneToMany
    @JoinColumn(name = "comment_id")*/



}
