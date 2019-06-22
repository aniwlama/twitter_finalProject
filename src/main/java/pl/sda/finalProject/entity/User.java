package pl.sda.finalProject.entity;

import lombok.Data;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userId;
    private String login;
    private String password;
    private String role;
    @Column(name="blocked_date")
    private LocalDateTime blockedDate;
    @Column(name="unblocked_date")
    private LocalDateTime unblockedDate;
    @Column(name="user_details_id")
    private int userDetailsId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "user_details_id")
    private UserDetails userDetails;


}
