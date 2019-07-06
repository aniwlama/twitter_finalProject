package pl.sda.finalProject.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import pl.sda.finalProject.entity.User;
import pl.sda.finalProject.exceptions.UnsupportedExceptions;
import pl.sda.finalProject.model.Roles;
import pl.sda.finalProject.model.UserDetailsDto;
import pl.sda.finalProject.model.UserDto;
import pl.sda.finalProject.repository.UserRepository;
import pl.sda.finalProject.service.validation.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;

    }

    public void saveUser(UserDto userDto, BindingResult bindingResult){


        Validator.validateExceptions(bindingResult);

         /*checking if there is no user with the same login already in the database*/
        if (userRepository.countByLogin(userDto.getLogin()) > 0) {
            throw new RuntimeException("User Exists. Please use different login");
        }

        //loginAvailability(userDto);

        userDto.setRole(Roles.ROLE_USER);
       /*setting join date automatically - works! Pattern needed to be added to the entity dto*/
        prepareJoinDate(userDto);


        User userToSave = modelMapper.map(userDto, User.class);
        userToSave.setPassword(passwordEncoder.encode(userToSave.getPassword()));


        userRepository.save(userToSave);
    }

    private void prepareJoinDate(UserDto userDto) {
        UserDetailsDto userDetailsDto = userDto.getUserDetailsDto();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newDateFormat = sdf.format(new Date());

        try{
            userDetailsDto.setJoinDate(sdf.parse(newDateFormat));
        } catch (ParseException e){
            e.printStackTrace();
            throw new UnsupportedExceptions(e.getMessage());
        }
    }

    /*to set a separate page for each error that might occurred during registration process*/
    /*public boolean loginAvailability (UserDto userDto){

        if(userRepository.countByLogin(userDto.getLogin()) == 0){
            return true;
        }
      return false;
    }*/

    /*to check which user is currently logged in*/
    public User getActiveUser (){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        /*UserDetails i getUsername są Springowe*/
        return userRepository.findUserByLogin(((UserDetails)principal).getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
