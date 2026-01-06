package com.almacen.stockyventas.repository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ProductoRestTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void existeElEndpointProductos() throws Exception{
        mockMvc.perform(get("/productos")).andExpect(status().isOk());

    }

    @Test
    void sePuedeHacerPostEnProductos() throws Exception{
        String productojson = """
                {
                    "nombre":"Termo",
                    "stock": 5,
                    "precio":250.0
                    }
                """;
        mockMvc.perform(post("/productos").contentType(MediaType.APPLICATION_JSON).content(productojson)) .andExpect(status().isCreated());
    }

    @Test
    void sePudeHacerPutEnProductos() throws Exception{
        String productojson = """
                {
                    "nombre":"Termo",
                    "stock": 5,
                    "precio":250.0
                    }
                """;
        mockMvc.perform(put("/producto/1").contentType(MediaType.APPLICATION_JSON).content(productojson)) .andExpect(status().isAccepted());
    }

    @Test
    void sePuedeHacerDeleteEnProductos() throws Exception{
        mockMvc.perform(delete("/producto/1")).andExpect(status().isAccepted());
    }


}
