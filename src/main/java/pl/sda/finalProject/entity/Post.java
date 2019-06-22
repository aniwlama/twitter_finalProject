package pl.sda.finalProject.entity;


import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    @NotNull
    private int postId;
    @Column(name="create_date")
    @NotNull
    private LocalDateTime createDate;
    @NotNull
    private String text;
    @Column(name="modify_date")
    private LocalDateTime modifyDate;
    @Column(name="delete_date")
    private LocalDateTime deleteDate;
    @NotNull
    private int userId;
    private int commentId;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "comment_id")
    private Comment comment;



}
