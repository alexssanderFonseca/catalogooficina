package br.com.oficina.catalogo.peca_insumo.adapter.in.controller;

import br.com.oficina.catalogo.peca_insumo.core.usecase.input.AtualizarPecaInsumoInput;
import br.com.oficina.catalogo.peca_insumo.core.usecase.input.CadastrarPecaInsumoInput;
import br.com.oficina.catalogo.peca_insumo.core.usecase.output.BuscarPecaInsumoPorIdUseCaseOutput;
import br.com.oficina.catalogo.peca_insumo.core.usecase.output.ListarPecasInsumoUseCaseOutput;
import br.com.oficina.catalogo.peca_insumo.adapter.in.controller.request.AtualizarPecaInsumoRequest;
import br.com.oficina.catalogo.peca_insumo.adapter.in.controller.request.CadastrarPecaInsumoRequest;
import br.com.oficina.catalogo.peca_insumo.adapter.in.controller.response.PecaInsumoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PecaInsumoControllerMapper {

    CadastrarPecaInsumoInput toInput(CadastrarPecaInsumoRequest request);

    @Mapping(target = "id", source = "id")
    @Mapping(source = "request.qtd", target = "quantidadeEstoque")
    @Mapping(source = "request.ativa", target = "ativo")
    AtualizarPecaInsumoInput toInput(UUID id, AtualizarPecaInsumoRequest request);

    PecaInsumoResponse toResponse(BuscarPecaInsumoPorIdUseCaseOutput pecaInsumo);

    PecaInsumoResponse toResponse(ListarPecasInsumoUseCaseOutput listaPecaInsumosOutput);
}
