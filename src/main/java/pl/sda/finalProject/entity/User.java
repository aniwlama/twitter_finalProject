package pl.sda.finalProject.entity;

import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    @NotNull
    private int userId;
    @Column(unique = true)
    @NotNull
    private String login;
    @NotNull
    private String password;
    @NotNull
    private String role;
    @Column(name="blocked_date")
    private LocalDateTime blockedDate;
    @Column(name="unblocked_date")
    private LocalDateTime unblockedDate;
    private int userDetailsId; //FK


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "user_details_id")
    private UserDetails userDetails;


}
