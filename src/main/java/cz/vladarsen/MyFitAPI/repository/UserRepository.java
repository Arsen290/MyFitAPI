package cz.vladarsen.MyFitAPI.repository;

import cz.vladarsen.MyFitAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
   User findByUsername(String username);
}
