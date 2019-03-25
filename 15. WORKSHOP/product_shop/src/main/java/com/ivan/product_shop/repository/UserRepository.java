package com.ivan.product_shop.repository;

import com.ivan.product_shop.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String > {

    User findByUsername(String username);
}
