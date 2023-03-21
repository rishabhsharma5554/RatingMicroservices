package com.rishabhtech.userservice.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rishabhtech.userservice.entity.User;

public interface UserRepo extends JpaRepository<User, String> {

}
