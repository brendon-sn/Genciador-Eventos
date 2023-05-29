package com.basis.RRM.builder;

import com.basis.RRM.dominio.Evento;
import com.basis.RRM.dominio.Motivo;
import com.basis.RRM.dominio.Situacao;
import com.basis.RRM.dominio.Usuario;
import com.basis.RRM.repository.EventoRepository;
import com.basis.RRM.service.dto.EventoDTO;
import com.basis.RRM.service.dto.SelectDTO;
import com.basis.RRM.service.mapper.EventoMapper;
import com.basis.RRM.service.mapper.UsuarioSelectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Component
public class EventoBuilder extends ConstrutorDeEntidade<Evento> {
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private EventoMapper eventoMapper;
    private UsuarioSelectMapper usuarioSelectMapper;
    @Autowired
    private MotivoBuilder motivoBuilder;
    @Autowired
    private UsuarioBuilder usuarioBuilder;

    protected Evento construirEntidade() throws ParseException {
        Evento evento = new Evento();
        Motivo motivo = motivoBuilder.construir();
        Situacao situacao = new Situacao();
        situacao.setId(1L);

        List<Usuario> usuarios = new ArrayList<>();
        if(usuarioBuilder.obterTodos().isEmpty()){
            Usuario usuario = usuarioBuilder.construir();
            usuarios.add(usuario);
        }else{
            usuarios.add(usuarioBuilder.obterTodos().iterator().next());
        }

        evento.setNome("Lanche dos brocados");
        evento.setDataEvento(LocalDate.now().plusMonths(2L));
        evento.setValor(80D);
        evento.setMotivo(motivo);
        evento.setSituacao(situacao);
        evento.setUsuario(usuarios);

        return evento;
    }

    @Override
    protected Evento persistir(Evento entidade) {
        return eventoRepository.save(entidade);
    }

    @Override
    protected Collection<Evento> obterTodos() {
        return eventoRepository.findAllOrderDate();
    }

    @Override
    protected Evento obterPorId(Long id) {
        return eventoRepository.getById(id);
    }

    @Override
    public Evento construir() throws ParseException {
        return super.construir();
    }
    public EventoDTO construirEntidadeDTO() throws ParseException{
        return eventoMapper.toDto(construirEntidade());
    }

    public void deletarTudo(){
        eventoRepository.deleteAll();
        usuarioBuilder.deleteAll();
        motivoBuilder.delete();
    }
}
