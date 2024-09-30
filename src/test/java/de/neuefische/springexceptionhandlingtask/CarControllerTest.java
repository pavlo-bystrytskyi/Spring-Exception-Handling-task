package de.neuefische.springexceptionhandlingtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getCarBrand_Failiure() throws Exception {
        //GIVEN
        String brand = "BMW";
        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/api/cars/{brand}", brand)
                )
                //THEN
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                        "message": "Only 'porsche' allowed"
                        }
                        """));
    }

    @Test
    void getCarBrand_Success() throws Exception {
        //GIVEN
        String brand = "porsche";
        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/api/cars/{brand}", brand)
                )
                //THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("porsche"));
    }

    @Test
    void getAllCars_Failiure() throws Exception {
        //GIVEN
        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/api/cars")
                )
                //THEN
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                        "message": "No Cars found"
                        }
                        """));
    }
}

