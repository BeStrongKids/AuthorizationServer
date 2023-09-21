package com.bestrongkids.chikingauthorizationserver.repositories;

import com.bestrongkids.chikingauthorizationserver.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findUserByName(String name);
    Optional<User> findUserByEmail(String email);
}
