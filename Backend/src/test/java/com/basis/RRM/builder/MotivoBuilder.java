package com.basis.RRM.builder;

import com.basis.RRM.dominio.Motivo;
import com.basis.RRM.repository.MotivoRepository;
import com.basis.RRM.service.dto.MotivoDTO;
import com.basis.RRM.service.mapper.MotivoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
@Component
public class MotivoBuilder extends ConstrutorDeEntidade<Motivo> {

    @Autowired
    private MotivoRepository motivoRepository;
    @Autowired
    private MotivoMapper motivoMapper;

    @Override
    protected Motivo construirEntidade() throws ParseException {
        Motivo motivo = new Motivo();

        motivo.setId(1L);
        motivo.setMotivo("aniversario");
        motivo.setDescricao("bla bla");
        return motivo;
    }

    @Override
    protected Motivo persistir(Motivo entidade) {
         return motivoRepository.save(entidade);
    }

    @Override
    protected Collection<Motivo> obterTodos() {
        return motivoRepository.findAll();
    }

    @Override
    protected Motivo obterPorId(Long id) {
        return motivoRepository.getById(id);
    }

    public MotivoDTO criarDTO()throws ParseException{
        return motivoMapper.toDto(construirEntidade());
    }

    public void delete(){
        motivoRepository.deleteAll();
    }
}
