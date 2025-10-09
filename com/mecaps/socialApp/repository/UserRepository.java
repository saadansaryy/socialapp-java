package com.mecaps.socialApp.repository;

import com.mecaps.socialApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//JPQL
//@Query

//DAO -> Data Access Object Layer
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "select * from user where email = ?1", nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Query("select * from user where email = ?1 and userName = ?2")
    Optional<User> findByEmailAndUserName(String email, String userName);
}
