package edu.pace.vgr.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import edu.pace.vgr.entities.UserAccount;
import edu.pace.vgr.entities.VideoGame;
import edu.pace.vgr.services.RatingService;
import edu.pace.vgr.services.UserAccountService;
import edu.pace.vgr.services.VideoGameService;

@WebMvcTest(RatingController.class)
@AutoConfigureMockMvc(addFilters = false)
class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideoGameService vgService;

    @MockBean
    private UserAccountService accountService;
    
    @MockBean
    private RatingService ratingService;

    @Test
    void displayRatingForm_ShouldAddRatingAndVideoGameToModelAndReturnView() throws Exception {

        long videoGameId = 1L;

        VideoGame mockGame = new VideoGame();
        mockGame.setVideoGameId(videoGameId);
        mockGame.setName("Test Game");

        UserAccount mockUser = new UserAccount();
        mockUser.setUserId(1L);
        mockUser.setUserName("testUser");

        when(vgService.findByVideoGameId(videoGameId)).thenReturn(mockGame);
        when(accountService.getLoggedInUser()).thenReturn(mockUser);


        mockMvc.perform(get("/ratings/new").param("videoGameId", String.valueOf(videoGameId)))
                .andExpect(status().isOk())
                .andExpect(view().name("ratings/new-rating"))
                .andExpect(model().attributeExists("rating"))
                .andExpect(model().attribute("videoGame", mockGame))
                .andExpect(model().attribute("rating", Matchers.hasProperty("user", is(mockUser))))
                .andExpect(model().attribute("rating", Matchers.hasProperty("videoGame", is(mockGame))))
                .andExpect(model().attribute("rating", Matchers.hasProperty("score", is(1))))
                .andExpect(model().attribute("rating", Matchers.hasProperty("reviewText", is(""))));
        
    }
}
