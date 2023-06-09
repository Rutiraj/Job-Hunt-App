 package com.stackroute.wishlist;

 import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
	

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.wishlist.model.Job;
import com.stackroute.wishlist.model.User;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
 	classes = WishlistApplication.class
 )
 @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 @AutoConfigureMockMvc
 class WishlistApplicationTests {
    private Job job1;
    private User user;
    private List<Job> jobs;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;



    @BeforeEach
    public void setUp() {

        job1 = new Job("2211", "java Full stack", "Hyderabad", "Developer", "Wipro","www.wipro.com");
        //job2 = new Job("2", "java Testing", "Banglore", "Tester", "Wipro","www.wipro.com");
        //job3 = new Job("3", "python", "Hyderabad", "developer", "Wipro","www.wipro.com");

        jobs = new ArrayList<>();
        jobs.add(job1);
        user = new User("abcd",jobs);
		//wishlistRepository.save(user);
    }

    @AfterEach
    public void tearDown() {
        job1 = null;
        // job2 = null;
        // job3 = null;
    }

    @Test
	@Order(1)
    public void givenJobDetailsWhenCreatedThenReturnSuccessCode() throws Exception {
        //when(service.saveJobToWishlist(job1,"siva")).thenReturn(user);

        MvcResult mvcResult = mockMvc.perform(post("/wishlist/abcd")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(job1)))
                .andExpect(status().isCreated())
                .andReturn();   

        assertThat(mapper.readValue(mvcResult.getResponse().getContentAsString(), User.class))
                .usingRecursiveComparison().isEqualTo(user);
    }

    @Test
	@Order(2)
    public void givenExistingJobDetailsWhenCreatedThenReturnConflictCode() throws Exception {

        mockMvc.perform(post("/wishlist/abcd")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(job1)))
                .andExpect(status().isConflict());
    }


@Test
	@Order(3)
    public void getJobsThenReturnSuccessCode() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/wishlist/abcd"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();   
        assertThat(mapper.readValue(mvcResult.getResponse().getContentAsString(), List.class))
		.hasSize(1);
    }



	@Test
	@Order(4)
    public void givenJobDetailsWhenDeletedThenReturnSuccessCode() throws Exception {

        MvcResult mvcResult = mockMvc.perform(delete("/wishlist/abcd/2211"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();   
        user.getJobList().clear();
        assertThat(mapper.readValue(mvcResult.getResponse().getContentAsString(), User.class))
                .usingRecursiveComparison().isEqualTo(user);
    }







    
}

