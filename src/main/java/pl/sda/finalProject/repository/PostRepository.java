package pl.sda.finalProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.finalProject.entity.Post;
import pl.sda.finalProject.entity.User;

import java.util.Date;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findPostByUserAndCreateDate(User user, Date createDate);
    Optional<Post> findPostByPostId(int postId);


}
