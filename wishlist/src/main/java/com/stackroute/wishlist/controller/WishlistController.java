package com.stackroute.wishlist.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.wishlist.exceptions.JobAlreadyExistsException;
import com.stackroute.wishlist.exceptions.JobNotFoundException;
import com.stackroute.wishlist.model.Job;
import com.stackroute.wishlist.model.User;
import com.stackroute.wishlist.service.WishlistService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST,
        RequestMethod.DELETE })
@RequestMapping("/wishlist")
public class WishlistController {

    private static Logger logger = LoggerFactory.getLogger(WishlistController.class);

    private ResponseEntity<?> responseEntity;
    @Autowired
    private WishlistService watchListService;

    @PostMapping("/{username}")
    public ResponseEntity<?> addJobToWatchList(@RequestBody Job job, @PathVariable String username)
            throws JobAlreadyExistsException {

        if (username != null) {
            try {
                User user1 = watchListService.saveJobToWishlist(job, username);
                logger.debug("reached");
                if (user1.getUsername() != null) {
                    responseEntity = new ResponseEntity<User>(user1, HttpStatus.CREATED);
                } else {
                    System.out.println("Username not found...maybe its null");
                }

            } catch (JobAlreadyExistsException e) {
                throw new JobAlreadyExistsException();
            } catch (Exception e) {
                responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

            }
            return responseEntity;

        } else {
            return null;
        }
    }

    @DeleteMapping("/{username}/{id}")
    public ResponseEntity<User> deleteFromList(@PathVariable String username, @PathVariable String id)
            throws JobNotFoundException {

        try {
            User user1 = watchListService.deleteJobFromWishlist(id, username);
            // responseEntity = new ResponseEntity<User>(user1, HttpStatus.OK);
            return ResponseEntity.ok(user1);
        } catch (JobNotFoundException e) {
            throw new JobNotFoundException();
        } // catch (Exception e) {
          // responseEntity = new ResponseEntity<>(e.getMessage(),
          // HttpStatus.INTERNAL_SERVER_ERROR);

        // }
        // return responseEntity;

    }

    @GetMapping("/{username}")
    public ResponseEntity<List<Job>> getJobList(@PathVariable String username) throws JobNotFoundException {

        try {
            List<Job> JobList1 = watchListService.getJobList(username);
            // responseEntity = new ResponseEntity<>(countryList, HttpStatus.OK);
            return ResponseEntity.ok(JobList1);
        } catch (Exception e) {
            throw new JobNotFoundException();
        }
        // return responseEntity;

    }
}
