package com.dlozano.app.rest.Repo;

import com.dlozano.app.rest.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepo extends JpaRepository<User, Long>{

}
