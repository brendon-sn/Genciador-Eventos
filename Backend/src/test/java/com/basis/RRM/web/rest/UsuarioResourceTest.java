package com.basis.RRM.web.rest;

import com.basis.RRM.RrmApplication;
import com.basis.RRM.builder.UsuarioBuilder;
import com.basis.RRM.dominio.Usuario;
import com.basis.RRM.service.dto.UsuarioDTO;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RrmApplication.class)
public class UsuarioResourceTest {
    private MockMvc mockMvc;
    private final String API_URL = "/api/usuario/";

    @Autowired
    private UsuarioBuilder usuarioBuilder;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void initTeste(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        usuarioBuilder.setCustomizacao(null);
        usuarioBuilder.deleteAll();
    }

    @Test @SneakyThrows
    public void listarTodosTest(){
        usuarioBuilder.construir();
        mockMvc.perform(
                get(API_URL+"filtro")
        ).andExpect(status().isOk());
    }

    @Test @SneakyThrows
    public void selectDtoUsuariosTest(){
        usuarioBuilder.construir();
        mockMvc.perform(
                get(API_URL)
        ).andExpect(status().isOk());
    }

    @Test @SneakyThrows
    public void buscarPorIdTest(){
        Usuario usuario = usuarioBuilder.construir();
        Long idUsuario = usuario.getId();
        mockMvc.perform(
                get(API_URL+idUsuario)
        ).andExpect(status().isOk());
    }

    @Test @SneakyThrows
    public void cadastrarUsuarioTest(){
        UsuarioDTO dto = usuarioBuilder.criaDTO();
        mockMvc.perform(
                post(API_URL).content(TestUtil.convertObjectToJsonBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    @Test @SneakyThrows
    public void editarUsuarioTest(){
        Usuario usuario = usuarioBuilder.construir();
        UsuarioDTO dto = usuarioBuilder.criaDTO();

        dto.setId(usuario.getId());
        dto.setTelefone("999999999");

        mockMvc.perform(
                put(API_URL).content(TestUtil.convertObjectToJsonBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test @SneakyThrows
    public void ativarUsuarioTest(){
        Usuario usuario = usuarioBuilder.construir();
        Long idUsuario = usuario.getId();
        usuarioBuilder.disableUsuario(idUsuario);
        mockMvc.perform(
                put(API_URL+idUsuario)
        ).andExpect(status().isOk());
    }

    @Test @SneakyThrows
    public void desativarUsuarioTest(){
        Usuario usuario = usuarioBuilder.construir();
        Long idUsuario = usuario.getId();
        mockMvc.perform(
                delete(API_URL+idUsuario)
        ).andExpect(status().isNoContent());
    }
}
