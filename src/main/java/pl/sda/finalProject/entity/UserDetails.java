package pl.sda.finalProject.entity;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Table(name = "user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_details_id")
    @NotNull
    private int userDetailsId;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String email;
    @Column(name="birth_date")
   //@DateTimeFormat(pattern = "yyyy-MM-dd") didn't work, annotation added to getter/object in UserDetailsDto!!
    @NotNull
    private Date birthDate;
    @Column(name="join_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date joinDate;


}
