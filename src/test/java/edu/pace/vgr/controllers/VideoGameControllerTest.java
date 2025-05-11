package edu.pace.vgr.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import edu.pace.vgr.entities.VideoGame;
import edu.pace.vgr.services.VideoGameService;

@WebMvcTest(VideoGameController.class)
@AutoConfigureMockMvc(addFilters = false)
public class VideoGameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideoGameService vgService;

    @Test
    void testDisplayOneVideoGame() throws Exception {

        Long gameId = 1L;
        VideoGame mockGame = new VideoGame();
        mockGame.setVideoGameId(gameId);
        mockGame.setName("Test Game");

        when(vgService.findByVideoGameId(gameId)).thenReturn(mockGame);

        mockMvc.perform(get("/videogames/{id}", gameId))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("videoGame"))
                .andExpect(view().name("video-games/one-video-game"));
        
    }
    
    @Test
    void testCreateVideoGameSuccess() throws Exception {
    	
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/videogames/save")
                .param("name", "Halo")
                .param("genres", "Shooter", "Action")
                .param("platforms", "Xbox", "PC");

        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/videogames"));
        
    }
    
    @Test
    void testCreateVideoGameValidationError() throws Exception {
    	
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/videogames/save")
                .param("name", "")
                .param("genres", "Shooter")
                .param("platforms", "PC");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(view().name("video-games/new-video-game"))
                .andExpect(model().attributeHasFieldErrors("videoGame", "name"));
        
    }
    
}
