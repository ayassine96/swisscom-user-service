package com.bluewin.userservice.repository;

import com.bluewin.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// This interface provides basic CRUD operations for the User entity by extending JpaRepository
public interface UserRepository extends JpaRepository<User, Long> {
}

