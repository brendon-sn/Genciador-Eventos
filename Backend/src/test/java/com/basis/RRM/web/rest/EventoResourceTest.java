package com.basis.RRM.web.rest;


import com.basis.RRM.RrmApplication;
import com.basis.RRM.builder.EventoBuilder;
import com.basis.RRM.builder.EventoBuilderSecundario;
import com.basis.RRM.dominio.Evento;
import com.basis.RRM.service.dto.EventoDTO;
import com.basis.RRM.util.TestUtil;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RrmApplication.class)
public class EventoResourceTest {

    private MockMvc mockMvc;
    private final String API_URL = "/api/evento/";

    @Autowired
    private EventoBuilder eventoBuilder;
    @Autowired
    private EventoBuilderSecundario eventoBuilderSecundario;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void initTest(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        eventoBuilder.setCustomizacao(null);
        eventoBuilder.deletarTudo();
        eventoBuilderSecundario.setCustomizacao(null);
        eventoBuilderSecundario.deletar();

    }

    @Test @SneakyThrows
    public void exibirTodos(){
        eventoBuilder.construir();
        mockMvc.perform(
                get(API_URL+"filtro")
        ).andExpect(status().isOk());
    }
    @Test @SneakyThrows
    public void exibirPorId(){
        Evento evento = eventoBuilder.construir();
        Long eventoId = evento.getId();
        mockMvc.perform(
                get(API_URL+eventoId)
        ).andExpect(status().isOk());
    }
    @Test @SneakyThrows
    public void criarEvento(){
        EventoDTO eventoDTO = eventoBuilder.construirEntidadeDTO();
        mockMvc.perform(
                post(API_URL).content(TestUtil.convertObjectToJsonBytes(eventoDTO))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }
    @Test @SneakyThrows
    public void editarEvento(){
        Evento evento = eventoBuilder.construir();
        EventoDTO eventoDTO = eventoBuilder.construirEntidadeDTO();
        eventoDTO.setId(evento.getId());
        mockMvc.perform(
                put(API_URL).content(TestUtil.convertObjectToJsonBytes(eventoDTO))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test @SneakyThrows
    public void adiarEvento(){
        Evento evento = eventoBuilder.construir();
        Long eventoId = evento.getId();
        mockMvc.perform(
                put(API_URL+"/adiar/"+eventoId)
        ).andExpect(status().isOk());
    }
    @Test @SneakyThrows
    public void trocarDatasEventos(){
        Evento eventoPrimario = eventoBuilder.construir();
        Evento eventoSecundario = eventoBuilderSecundario.construir();
        Long idEventoPrimario = eventoPrimario.getId();
        Long idEventoSecundario = eventoSecundario.getId();

        mockMvc.perform(
                put(API_URL+"trocar/"+idEventoPrimario+"/"+idEventoSecundario)
        ).andExpect(status().isOk());
    }

    @Test @SneakyThrows
    public void cancelarEventoTest(){
        Evento evento = eventoBuilder.construir();
        Long idEvento = evento.getId();
        mockMvc.perform(
                delete(API_URL+idEvento)
        ).andExpect(status().isNoContent());
    }

}
