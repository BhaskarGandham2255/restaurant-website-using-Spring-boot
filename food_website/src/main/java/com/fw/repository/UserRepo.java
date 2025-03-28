package com.fw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fw.Entity.User;

public interface UserRepo extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);
}
