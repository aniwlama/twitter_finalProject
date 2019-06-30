package pl.sda.finalProject.entity;


import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
    private Date createDate;
    @NotNull
    private String text;
    @Column(name="modify_date")
    private LocalDateTime modifyDate;
    @Column(name="delete_date")
    private LocalDateTime deleteDate;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="comment_id")
    private List<Comment> comments;

    public void setUser(User user) {
        this.user = user;
    }
}
