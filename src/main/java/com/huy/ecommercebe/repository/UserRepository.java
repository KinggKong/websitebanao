package com.huy.ecommercebe.repository;

import com.huy.ecommercebe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
