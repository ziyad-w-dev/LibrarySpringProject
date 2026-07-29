package com.ziyad.libraryspringproject.repository;

import com.ziyad.libraryspringproject.domain.entity.Role;
import com.ziyad.libraryspringproject.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
    Optional<User> findByUserName(String username);
    boolean existsByRole(Role role);

}
