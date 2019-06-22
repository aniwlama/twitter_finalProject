package pl.sda.finalProject.entity;

import lombok.Data;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String login;
    private String password;
    private String role;
    private LocalDateTime blockedDate;
    private LocalDateTime unblockedDate;
    private int userDetailsId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "user_details_id")
    private UserDetails userDetails;


}
