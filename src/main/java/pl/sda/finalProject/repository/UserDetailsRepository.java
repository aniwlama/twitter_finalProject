package pl.sda.finalProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.finalProject.entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}
