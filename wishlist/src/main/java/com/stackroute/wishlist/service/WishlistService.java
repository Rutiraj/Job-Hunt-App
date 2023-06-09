package com.stackroute.wishlist.service;

import java.util.List;

import com.stackroute.wishlist.exceptions.JobAlreadyExistsException;
import com.stackroute.wishlist.exceptions.JobNotFoundException;
import com.stackroute.wishlist.model.Job;
import com.stackroute.wishlist.model.User;

public interface WishlistService {
    public User saveJobToWishlist(Job job, String username) throws JobAlreadyExistsException;

    public User deleteJobFromWishlist(String trackId, String username) throws JobNotFoundException;

    public List<Job> getJobList(String username) throws Exception;

}
