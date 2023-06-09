// package com.stackroute.wishlist.controller;

// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// //import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MvcResult;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.stackroute.wishlist.exceptions.JobAlreadyExistsException;
// import com.stackroute.wishlist.model.Job;
// import com.stackroute.wishlist.model.User;
// import com.stackroute.wishlist.service.WishlistService;

// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// import java.util.ArrayList;
// import java.util.List;

// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.assertj.core.api.Assertions.assertThat;

// //import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// @WebMvcTest(controllers = WishlistController.class)
// public class WishlistControllerTest {

//     private Job job1;
//     private User user;
//     private List<Job> jobs;

//     @Autowired
//     private MockMvc mockMvc;

//     @Autowired
//     private WishlistService service;

//     @Autowired
//     private ObjectMapper mapper;



//     @BeforeEach
//     public void setUp() {

//         job1 = new Job("1", "java Full stack", "Hyderabad", "Developer", "Wipro","www.wipro.com");
//         //job2 = new Job("2", "java Testing", "Banglore", "Tester", "Wipro","www.wipro.com");
//         //job3 = new Job("3", "python", "Hyderabad", "developer", "Wipro","www.wipro.com");

//         jobs = new ArrayList<>();
//         jobs.add(job1);
//         user = new User("siva",jobs);
//     }

//     @AfterEach
//     public void tearDown() {
//         job1 = null;
//         // job2 = null;
//         // job3 = null;
//     }

//     @Test
//     public void givenJobDetailsWhenCreatedThenReturnSuccessCode() throws Exception {
//         when(service.saveJobToWishlist(job1,"siva")).thenReturn(user);

//         MvcResult mvcResult = mockMvc.perform(post("/wishlist/username")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(mapper.writeValueAsString(job1)))
//                 .andExpect(status().isCreated())
//                 .andReturn();   

//         assertThat(mapper.readValue(mvcResult.getResponse().getContentAsString(), WishlistService.class))
//                 .usingRecursiveComparison().isEqualTo(job1);
//     }

//     @Test
//     public void givenExistingJobDetailsWhenCreatedThenReturnConflictCode() throws Exception {
//         when(service.saveJobToWishlist(job1,"siva")).thenThrow(new JobAlreadyExistsException());

//         mockMvc.perform(post("/wishlist/username")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(mapper.writeValueAsString(job1)))
//                 .andExpect(status().isConflict());
//     }







    
// }
