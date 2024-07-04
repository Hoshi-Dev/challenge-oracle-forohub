package com.alura.forohub.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    UserDetails findByName(String username);

    boolean existsByEmail(String email);

    boolean existsByName(String name);
}
