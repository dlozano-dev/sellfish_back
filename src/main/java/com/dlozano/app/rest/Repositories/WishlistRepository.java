package com.dlozano.app.rest.Repositories;

import com.dlozano.app.rest.Models.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Long>{

}
