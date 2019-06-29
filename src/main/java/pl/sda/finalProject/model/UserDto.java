package pl.sda.finalProject.model;

public class UserDto {

    //@Size(min = 3, max = 20, message = "Username should be between 3 and 20 characters")
    private String login;
    //@Size(min = 3, max = 30, message = "Password should be between 3 and 30 characters")
    private String password;
    private Roles role;
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

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;

    }
}


