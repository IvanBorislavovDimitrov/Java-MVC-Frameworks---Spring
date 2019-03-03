package com.ivan.repositories;

import com.ivan.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    int countAllByEmail(String email);

    int countAllByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}
