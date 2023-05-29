package com.basis.RRM.web.rest;


import com.basis.RRM.service.UsuarioService;
import com.basis.RRM.service.dto.SelectDTO;
import com.basis.RRM.service.dto.UsuarioDTO;
import com.basis.RRM.service.dto.UsuarioListagemDTO;
import com.basis.RRM.service.filter.UsuarioFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/usuario")
@RequiredArgsConstructor
public class UsuarioResource {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<SelectDTO>> listarUsuariosEmSelect(){
        return ResponseEntity.ok(usuarioService.listarUsuariosEmSelect());
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<UsuarioListagemDTO>> exibirUsuariosFiltrado(UsuarioFilter usuario){
        return ResponseEntity.ok(usuarioService.mostrarTodosUsuariosFiltrado(usuario));
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioDTO> exibirUsuarioPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(usuarioService.mostrarUsuarioPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO salvo = usuarioService.salvarUsuario(usuarioDTO);
        return ResponseEntity.created(URI.create("api/usuario/filtro?id="+salvo.getId())).body(salvo);
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.editarUsuario(usuarioDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<UsuarioDTO> ativarUsuario(@Valid @PathVariable("id") Long id){
        return ResponseEntity.ok(usuarioService.ativarusuario(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarUsuario(@Valid @PathVariable("id") Long id) {
        usuarioService.inativarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

