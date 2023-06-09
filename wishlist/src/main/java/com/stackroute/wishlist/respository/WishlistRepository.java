package com.stackroute.wishlist.respository;

//import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.wishlist.model.User;

public interface WishlistRepository extends MongoRepository<User, String> {
    public User findByusername(String username);

}
