package br.com.oficina.catalogo.peca_insumo.core.port.in;

import br.com.oficina.catalogo.peca_insumo.adapter.in.controller.request.DarBaixaEstoqueRequest;

import java.util.List;

public interface ReporEstoquePecaInsumoUseCase {
    void reporEstoque(List<DarBaixaEstoqueRequest> requests);
}
