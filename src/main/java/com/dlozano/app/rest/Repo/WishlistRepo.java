package com.dlozano.app.rest.Repo;

import com.dlozano.app.rest.Models.User;
import com.dlozano.app.rest.Models.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepo extends JpaRepository<Wishlist, Long>{

}
