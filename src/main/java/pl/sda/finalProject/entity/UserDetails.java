package pl.sda.finalProject.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_details_id")
    private int userDetailsId;
    private String name;
    private String surname;
    private String email;
    @Column(name="birth_date")
    private LocalDate birthDate;
    @Column(name="join_date")
    private LocalDateTime joinDate;


}
