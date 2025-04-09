package com.dlozano.app.rest.Repo;

import com.dlozano.app.rest.Models.ProfilePicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfilePictureRepository extends JpaRepository<ProfilePicture, Long>{
    Optional<ProfilePicture> findByUserId(int userId);
}
