package com.acme.studenthome;

import com.acme.studenthome.domain.model.UserAccountSystem.User;
import com.acme.studenthome.domain.service.UserAccountSystemService.UserService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class UserTests {
    @Autowired
    private UserService userService;

    @MockBean
    private MockMvc mockMvc;

    /*@Test
    @DisplayName("GET /widgets success")
    void testGetWidgetsSuccess() throws Exception {
        User user1 = new User();
        user1.setId((long) 1);
        user1.setEmail("magotor1304@gmail.com");
        user1.setPassword("pacheco98");
        User user2 = new User();
        user2.setId((long) 2);
        user2.setEmail("cesar1304@gmail.com");
        user2.setPassword("cesar98");
        doReturn(Lists.newArrayList(user1, user2)).when(userService).findAll();
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string(HttpHeaders.LOCATION, "/api/users"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].email", is("magotor1304@gmail.com")))
                .andExpect(jsonPath("$[0].password", is("pacheco98")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].email", is("cesar1304@gmail.com")))
                .andExpect(jsonPath("$[1].password", is("cesar98")));
    }*/

    @Test
    @DisplayName("GET /api/users/1")
    void testGetUserById() throws Exception {
        User user = new User();
        user.setId((long) 1);
        user.setEmail("magotor130@gmail.com");
        user.setPassword("pacheco98");
        doReturn(Optional.of(user)).when(userService).getUserById((long) 1);

        mockMvc.perform(get("/api/users/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string(HttpHeaders.LOCATION, "/api/users/1"))
                .andExpect(header().string(HttpHeaders.ETAG, "\"1\""))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.email", is("magotor1304@gmail.com")))
                .andExpect(jsonPath("$.password", is("pacheco98")));
    }

    @Test
    @DisplayName("GET /api/users/1 - Not Found")
    void testGetUserByIdNotFound() throws Exception {
        doReturn(Optional.empty()).when(userService).getUserById((long) 2);
        mockMvc.perform(get("/api/users/{id}", 2))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /api/users")
    void testUserSave() throws Exception{
        User userToPost = new User();
        userToPost.setEmail("magotor1304@gmail.com");
        userToPost.setPassword("pacheco98");
        User userToReturn = new User();
        userToReturn.setId((long) 1);
        userToReturn.setEmail("magotor1304@gmail.com");
        userToReturn.setPassword("pacheco98");
        Gson gson = new Gson();
        doReturn(userToReturn).when(userService).createUser(any());

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(userToPost)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string(HttpHeaders.LOCATION, "/api/users/1"))
                .andExpect(header().string(HttpHeaders.ETAG, "\"1\""))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.email", is("magotor1304@gmail.com")))
                .andExpect(jsonPath("$.password", is("pacheco98")));

    }
}
