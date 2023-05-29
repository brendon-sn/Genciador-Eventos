package com.basis.RRM.web.rest;


import com.basis.RRM.RrmApplication;
import com.basis.RRM.builder.MotivoBuilder;
import com.basis.RRM.dominio.Motivo;
import com.basis.RRM.service.dto.MotivoDTO;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RrmApplication.class)
public class MotivoResourceTest {
    private MockMvc mockMvc;
    private final String API_URL = "/api/motivo/";

    @Autowired
    private MotivoBuilder motivoBuilder;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void initTest(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        motivoBuilder.setCustomizacao(null);
        motivoBuilder.delete();
    }

    @Test @SneakyThrows
    public void exibirTodos(){
        motivoBuilder.construir();
        mockMvc.perform(
                get(API_URL)
        ).andExpect(status().isOk());
    }
    @Test @SneakyThrows
    public void obterMotivoPorId (){
        Motivo motivo = motivoBuilder.construir();
        Long idMotivo = motivo.getId();
        mockMvc.perform(
                get(API_URL+idMotivo)
        ).andExpect(status().isOk());

    }

    @Test @SneakyThrows
    public void exibirMotivosEmSelect(){
        motivoBuilder.construir();
        mockMvc.perform(
                get(API_URL+"select")
        ).andExpect(status().isOk());
    }
    @Test @SneakyThrows
    public void salvarMotivo(){
        MotivoDTO motivoDTO = motivoBuilder.criarDTO();
        mockMvc.perform(
                post(API_URL).content(TestUtil.convertObjectToJsonBytes(motivoDTO))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }
    @Test @SneakyThrows
    public void atualizarMotivo(){
        Motivo motivo = motivoBuilder.construir();
        MotivoDTO motivoDTO = motivoBuilder.criarDTO();
        motivoDTO.setId(motivo.getId());
        mockMvc.perform(
                put(API_URL).content(TestUtil.convertObjectToJsonBytes(motivoDTO))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
    @Test @SneakyThrows
    public void deletar(){
        Motivo motivo = motivoBuilder.construir();
        Long motivoId = motivo.getId();
        mockMvc.perform(
                delete(API_URL+motivoId)
        ).andExpect(status().isOk());
    }

}
