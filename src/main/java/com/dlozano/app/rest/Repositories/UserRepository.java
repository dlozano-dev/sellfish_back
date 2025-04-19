package com.dlozano.app.rest.Repositories;

import com.dlozano.app.rest.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    @Query("SELECT u.username FROM User u WHERE LOWER(u.username) LIKE LOWER(CONCAT(:prefix, '%'))")
    List<String> findTop50UsernamesByPrefix(@Param("prefix") String prefix);

    Optional<User> findByUsername(String username);
}
