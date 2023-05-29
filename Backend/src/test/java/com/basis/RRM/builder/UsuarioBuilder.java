package com.basis.RRM.builder;

import com.basis.RRM.dominio.Cargo;
import com.basis.RRM.dominio.Usuario;
import com.basis.RRM.repository.UsuarioRepository;
import com.basis.RRM.service.dto.UsuarioDTO;
import com.basis.RRM.service.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;

@Component
public class UsuarioBuilder extends ConstrutorDeEntidade<Usuario> {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    protected Usuario construirEntidade() throws ParseException {
        Usuario usuario = new Usuario();
        usuario.setNome("Carolina Rocha Ribeiro");
        usuario.setCpf("79552875684");
        usuario.setDataNascimento(LocalDate.now().minusYears(25));
        usuario.setEmail(usuario.getCpf()+"@yopmail.com");
        Cargo cargo = new Cargo();
        cargo.setId(1L);
        usuario.setCargo(cargo);
        usuario.setStatus(true);
        return usuario;
    }

    @Override
    protected Usuario persistir(Usuario entidade) {
        usuarioRepository.save(entidade);
        return entidade;
    }

    @Override
    protected Collection<Usuario> obterTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    protected Usuario obterPorId(Long id) {
        return usuarioRepository.getById(id);
    }

    public UsuarioDTO criaDTO() throws ParseException{
        return usuarioMapper.toDto(construirEntidade());
    }

    public void deleteAll(){
        usuarioRepository.deleteAll();
    }

    public void disableUsuario(Long id){
        Usuario usuario = usuarioRepository.getById(id);
        usuario.setStatus(false);
        usuarioRepository.save(usuario);
    }
}
