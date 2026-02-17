package br.com.oficina.catalogo.peca_insumo.core.port.in;

import br.com.oficina.catalogo.peca_insumo.core.usecase.output.BuscarPecaInsumoPorIdUseCaseOutput;

import java.util.UUID;

public interface BuscarPecaInsumoPorIdUseCase {

    BuscarPecaInsumoPorIdUseCaseOutput executar(UUID id);
}
