package br.com.oficina.catalogo.servico.adapters.out.persistence.mapper;

import br.com.oficina.catalogo.servico.core.domain.entity.Servico;
import br.com.oficina.catalogo.servico.adapters.out.persistence.entity.ServicoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServicoRepositoryMapper {

    Servico toDomain(ServicoEntity entity);

    ServicoEntity toEntity(Servico domain);

}
