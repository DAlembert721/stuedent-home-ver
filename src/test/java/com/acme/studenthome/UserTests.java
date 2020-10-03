package com.acme.studenthome;

import com.acme.studenthome.domain.model.UserAccountSystem.User;
import com.acme.studenthome.domain.service.UserAccountSystemService.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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

    @Test
    @DisplayName("POST /api/users")
    void testSave() throws Exception{
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
