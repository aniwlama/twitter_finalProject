package pl.sda.finalProject.model;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class UserDto {

    //@Size(min = 3, max = 20, message = "Username should be between 3 and 20 characters")
    private String login;
    //@Size(min = 3, max = 30, message = "Password should be between 3 and 30 characters")
    private String password;
    private String role;
    private UserDetailsDto userDetailsDto;
    //private LocalDateTime blockedDate;
    //private LocalDateTime unblockedDate;

    public UserDetailsDto getUserDetailsDto() {
        return userDetailsDto;
    }

    public void setUserDetailsDto(UserDetailsDto userDetailsDto) {
        this.userDetailsDto = userDetailsDto;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;

    }
}


