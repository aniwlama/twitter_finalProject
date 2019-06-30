package pl.sda.finalProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.finalProject.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
