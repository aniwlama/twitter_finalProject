package pl.sda.finalProject.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.finalProject.entity.User;
import pl.sda.finalProject.model.UserDetailsDto;
import pl.sda.finalProject.model.UserDto;
import pl.sda.finalProject.repository.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public void saveUser(UserDto userDto) throws ParseException {
        userDto.setRole("USER");

        // setting join date automatically - works! Pattern needed to be added to the entity
        UserDetailsDto userDetailsDto = userDto.getUserDetailsDto();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newDateFormat = sdf.format(new Date());
        userDetailsDto.setJoinDate(sdf.parse(newDateFormat));
        userDto.setUserDetailsDto(userDetailsDto);
        //
        User userToSave = modelMapper.map(userDto, User.class);
        userRepository.save(userToSave);
    }

}
