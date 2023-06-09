package com.stackroute.wishlist.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.stackroute.wishlist.exceptions.JobAlreadyExistsException;
import com.stackroute.wishlist.exceptions.JobNotFoundException;
import com.stackroute.wishlist.model.Job;
import com.stackroute.wishlist.model.User;

public class WishlistImplTest {
    private User user;
    private Job job1;
    List<Job> list;

    @BeforeEach
    public void setUp() {
        user = new User();
        job1 = new Job();
        job1.setId("36");
        job1.setName("Full stack");
        job1.setLocation("Hyderabad");
        job1.setRole("developer");
        job1.setCompany("wipro");
        job1.setLanding_page("www.wipro.com");
        list = new ArrayList<>();
        list.add(job1);
        user.setJobList(list);
    }

    @AfterEach
    public void tearDown() {
        job1 = null;
        user = null;
    }

    @Test
    public void testSaveWishlistForUser() throws JobAlreadyExistsException {
        WishlistImpl service = mock(WishlistImpl.class);
        when(service.saveJobToWishlist(job1, "siva")).thenReturn(user);
        User dummy = service.saveJobToWishlist(job1, "siva");
        assertNotNull(dummy);
    }

    @Test
    public void testViewWishlistForUser() throws Exception {
        WishlistImpl service = mock(WishlistImpl.class);
        job1 = new Job();
        job1.setId("12");
        job1.setName("python");
        job1.setLocation("hyderabad");
        job1.setRole("developer");
        job1.setCompany("wipro");
        job1.setLanding_page("www.wipro.com");
        list.add(job1);
        user.setJobList(list);
        when(service.getJobList("siva")).thenReturn(list);
        List<Job> list1 = service.getJobList("siva");
        assertNotNull(list1);
        assertEquals(list1.size(), list.size());

    }

    @Test
    public void testDeleteWishlistForUser() throws JobNotFoundException {
        WishlistImpl service = mock(WishlistImpl.class);
        when(service.deleteJobFromWishlist("12", "siva")).thenReturn(null);
        User dummy = service.deleteJobFromWishlist("12", "siva");
        assertNull(dummy);

    }
}