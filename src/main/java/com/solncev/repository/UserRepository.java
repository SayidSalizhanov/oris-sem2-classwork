package com.solncev.repository;

import com.solncev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query(value = "select u from User u where u.name = :name")
    List<User> findByName(String name);

    @Query(value = "select * from users u where u.name = ?1", nativeQuery = true)
    List<User> findAllByName(String name);

    Optional<User> findByVerificationCode(String code);
}
