package com.basis.RRM.service.filter;

import com.basis.RRM.dominio.Evento;
import com.basis.RRM.dominio.Evento_;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Getter
@Setter
public class EventoFilter implements EntityFilter<Evento> {
    private String nome;
    private String data;
    private String motivo;
    private String situacao;
    private String usuario;


    @Override
    public Specification<Evento> filtrar() {
        return (root, cq, cb) -> cb.and(getPredicates(root, cq, cb).toArray(new Predicate[0]));
    }

    private List<Predicate> getPredicates (Root<Evento>root, CriteriaQuery<?>cq, CriteriaBuilder cb){
        List<Predicate> predicates = new ArrayList<>();
        cq.orderBy(cb.asc(root.get("dataEvento")));

        if (nome != null){
            predicates.add(cb.like(root.get(Evento_.nome), "%"+ nome + "%"));
        }
        if (data != null){
            predicates.add(cb.equal(root.get(Evento_.dataEvento), LocalDate.parse(data)));
        }
        if (motivo != null){
            predicates.add(cb.like(root.join("motivo").get("motivo"),"%"+ motivo+"%"));
        }
        if (situacao != null){
            predicates.add(cb.like(root.join("situacao").get("situacao"),"%"+ situacao+"%"));
        }
        if (usuario != null){
            predicates.add(cb.like(root.join("usuario").get("nome"),"%"+ usuario+"%"));
        }
        return predicates;
    }

}
