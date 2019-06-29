package pl.sda.finalProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.finalProject.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByLogin(String login);

    Long countByLogin (String login);
}
