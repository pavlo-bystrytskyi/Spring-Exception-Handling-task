package de.neuefische.springexceptionhandlingtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class AnimalControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void getAnimalSpeciesTest_success() throws Exception {
        //GIVEN
        String species = "dog";
        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/api/animals/{species}", species)
                )
                //THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("dog"));
    }


    @Test
    void getAnimalSpeciesTest_failure() throws Exception {
        //GIVEN
        String species = "cat";
        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/api/animals/{species}", species)
                )
                //THEN
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                        "message": "Only 'dog' is allowed"
                        }
                        """));
    }

    @Test
    void getAllAnimalsTest_failure() throws Exception {
        //GIVEN
        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/api/animals")
                )
                //THEN
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                        "message": "No Animals found"
                        }
                        """));
    }
}