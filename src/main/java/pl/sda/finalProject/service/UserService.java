package pl.sda.finalProject.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.finalProject.entity.User;
import pl.sda.finalProject.model.UserDto;
import pl.sda.finalProject.repository.UserRepository;


@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public void saveUser(UserDto userDto) {
        userDto.setRole("USER");
        User userToSave = modelMapper.map(userDto, User.class);
        userRepository.save(userToSave);

    }
}
