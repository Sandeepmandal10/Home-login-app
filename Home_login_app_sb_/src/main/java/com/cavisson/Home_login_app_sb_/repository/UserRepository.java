package com.cavisson.Home_login_app_sb_.repository;

import com.cavisson.Home_login_app_sb_.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmailAndPassword(String email, String password);
}
