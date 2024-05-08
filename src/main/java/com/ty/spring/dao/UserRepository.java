package com.ty.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ty.spring.entity.User;
@Repository

public interface UserRepository  extends JpaRepository<User, Long> {
    User findByEmail(String email);

}
