package com.stackroute.wishlist.service;

import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.wishlist.exceptions.JobAlreadyExistsException;
import com.stackroute.wishlist.exceptions.JobNotFoundException;
import com.stackroute.wishlist.model.Job;
import com.stackroute.wishlist.model.User;
import com.stackroute.wishlist.respository.WishlistRepository;

@Service
public class WishlistImpl implements WishlistService {
    @Autowired
    private WishlistRepository watchListRepository;

    @Override
    public User saveJobToWishlist(Job job, String username) throws JobAlreadyExistsException {
        User user1 = watchListRepository.findByusername(username);
        // User user1 = user2.get();
        if (user1 == null) {
            user1 = new User(username, new ArrayList<Job>());
        }
        List<Job> JobList1 = user1.getJobList();

        if (JobList1 != null) {
            for (Job p : JobList1) {

                if (p.getId().equals(job.getId())) {
                    throw new JobAlreadyExistsException();
                }
            }

            JobList1.add(job);

            System.out.println("Saving Data if block");
            user1.setJobList(JobList1);
            watchListRepository.save(user1);
        }

        else {
            JobList1 = new ArrayList<>();
            JobList1.add(job);

            user1.setJobList(JobList1);
            watchListRepository.save(user1);
        }
        return user1;

    }

    @Override
    public User deleteJobFromWishlist(String id, String username) throws JobNotFoundException {
        User user2 = watchListRepository.findByusername(username);
        // User user1 = user2.get();
        int indexnum = 0;
        List<Job> JobList1 = user2.getJobList();
        boolean status = false;
        if (JobList1!= null && JobList1.size() > 0) {
            for (Job t : JobList1) {
                indexnum++;
                if (t.getId().equals(id)) {
                    status = true;
                    JobList1.remove(indexnum - 1);
                    user2.setJobList(JobList1);
                    watchListRepository.save(user2);
                    break;
                }
            }
            if (status == false) {
                throw new JobNotFoundException();
            }
        }
        return user2;
    }

    @Override
    public List<Job> getJobList(String username) throws Exception {
        User user1 = watchListRepository.findByusername(username);
        // User user2 = user1.get();
        return user1.getJobList();
    }

    
}
