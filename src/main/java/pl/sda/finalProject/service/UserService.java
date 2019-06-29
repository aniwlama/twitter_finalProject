package pl.sda.finalProject.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import pl.sda.finalProject.entity.User;
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


    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;

    }

    public void saveUser(UserDto userDto, BindingResult bindingResult) throws ParseException {


        Validator.validateExceptions(bindingResult);

         /*checking if there is no user with the same login already in the database*/
        if (userRepository.countByLogin(userDto.getLogin()) > 0) {
            throw new RuntimeException("User Exists. Please use different login");
        }

        //loginAvailability(userDto);

        userDto.setRole(Roles.USER);
       /*  setting join date automatically - works! Pattern needed to be added to the entity*/
        UserDetailsDto userDetailsDto = userDto.getUserDetailsDto();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newDateFormat = sdf.format(new Date());
        userDetailsDto.setJoinDate(sdf.parse(newDateFormat));
        userDto.setUserDetailsDto(userDetailsDto);

        User userToSave = modelMapper.map(userDto, User.class);
        userRepository.save(userToSave);
    }

    /*public String loginAvailability (UserDto userDto){

        if (userRepository.countByLogin(userDto.getLogin()) != 0) {

        }
        return "errorLogin";
    }*/

}
