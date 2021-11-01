package com.ceiba.mascota.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.mascota.comando.ComandoMascota;
import com.ceiba.mascota.servicio.testdatabuilder.ComandoMascotaTestDataBuilder;
import com.ceiba.usuario.controlador.ComandoControladorUsuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorUsuario.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorMascotaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    @DisplayName("Deberia crear una mascota")
    void deberiaCrearUnaMascota() throws Exception{
        // arrange
        ComandoMascota mascota = new ComandoMascotaTestDataBuilder()
                .conNombre("toboias")
                .conUsuario(1L)
                .conTipoMascota(2L)
                .build();
        // act - assert
        mocMvc.perform(post("/mascotas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mascota)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 2}"));
    }

    @Test
    @DisplayName("No Deberia crear una mascota, dado que ya existe")
    void NodeberiaCrearUnaMascota() throws Exception{
        // arrange
        ComandoMascota mascota = new ComandoMascotaTestDataBuilder()
                .conNombre("anibal")
                .conUsuario(1L)
                .conTipoMascota(2L)
                .build();
        // act - assert
        mocMvc.perform(post("/mascotas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mascota)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'nombreExcepcion': 'ExcepcionDuplicidad'}"))
                .andExpect(content().json("{'mensaje': 'El mascota ya existe en el sistema'}"));
    }

    @Test
    @DisplayName("Deberia actualizar una mascota")
    void deberiaActualizarUnaMascota() throws Exception{
        // arrange
        Long id = 1L;
        ComandoMascota mascota = new ComandoMascotaTestDataBuilder()
                .conNombre("tobias")
                .conUsuario(1L)
                .conTipoMascota(1L)
                .conId(id)
                .build();
        // act - assert
        mocMvc.perform(put("/mascotas",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mascota)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deberia eliminar una mascota")
    void deberiaEliminarUnaMascota() throws Exception {
        // arrange
        Long idMascota = 1L;
        // act - assert
        mocMvc.perform(delete("/mascotas/{idMascota}",idMascota)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mocMvc.perform(get("/mascotas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
