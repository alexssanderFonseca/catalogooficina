package br.com.oficina.catalogo.servico.adapters.in.controller.mapper;


import br.com.oficina.catalogo.servico.core.usecase.input.AtualizarServicoInput;
import br.com.oficina.catalogo.servico.core.usecase.input.CadastrarServicoInput;
import br.com.oficina.catalogo.servico.adapters.in.controller.request.AtualizarServicoRequest;
import br.com.oficina.catalogo.servico.adapters.in.controller.request.CadastrarServicoRequest;
import br.com.oficina.catalogo.servico.adapters.in.controller.response.ServicoResponse; // New import
import br.com.oficina.catalogo.servico.core.domain.entity.Servico; // New import
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ServicoRequestMapper {

    CadastrarServicoInput toInput(CadastrarServicoRequest cadastrarServicoInput);

    @Mapping(target = "id", source = "id")
    AtualizarServicoInput toInput(UUID id, AtualizarServicoRequest atualizarServicoRequest);

    ServicoResponse toResponse(Servico servico);
}
