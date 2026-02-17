package br.com.oficina.catalogo.peca_insumo.core.port.in;

import br.com.oficina.catalogo.peca_insumo.core.usecase.input.AtualizarPecaInsumoInput;

public interface AtualizarPecaInsumoUseCase {

    void executar(AtualizarPecaInsumoInput input);
}
