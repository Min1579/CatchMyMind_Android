package com.min.catchmymind.CatchMyMind_backend.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>{
    @Query(value = "SELECT USER_ID FROM USER WHERE EMAIL =:email AND PASSWORD =:password", nativeQuery = true)
    Long findUserByEmailAndPassword(@Param("email") final String email,@Param("password") final String password);
}
