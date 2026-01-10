package com.almacen.stockyventas.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.almacen.stockyventas.BaseTest;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ProductoRestTest extends BaseTest {
    
    private String productoJson;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
    productoJson = """
            {
                "nombre":"Termo",
                "stock": 5,
                "precio":250.0
                }
            """;
    }

    @Test
    void existeElEndpointProductos() throws Exception{
        mockMvc.perform(get("/productos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));


    }

    @Test
    void sePuedeHacerPostEnProductos() throws Exception{
        mockMvc.perform(post("/productos").contentType(MediaType.APPLICATION_JSON).content(productoJson)) .andExpect(status().isCreated());
    }

    @Test
    void sePudeHacerPutEnProductos() throws Exception{
        mockMvc.perform(put("/producto/1").contentType(MediaType.APPLICATION_JSON).content(productoJson)) .andExpect(status().isAccepted());
    }

    @Test
    void sePuedeHacerDeleteEnProductos() throws Exception{
        mockMvc.perform(delete("/producto/1")).andExpect(status().isAccepted());
    }


}
