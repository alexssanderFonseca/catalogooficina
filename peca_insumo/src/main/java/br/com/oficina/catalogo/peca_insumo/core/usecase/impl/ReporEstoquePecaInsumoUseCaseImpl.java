package br.com.oficina.catalogo.peca_insumo.core.usecase.impl;

import br.com.oficina.catalogo.peca_insumo.adapter.in.controller.request.DarBaixaEstoqueRequest;
import br.com.oficina.catalogo.peca_insumo.core.port.in.ReporEstoquePecaInsumoUseCase;
import br.com.oficina.catalogo.peca_insumo.core.port.out.PecaInsumoRepository;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Named
@RequiredArgsConstructor
public class ReporEstoquePecaInsumoUseCaseImpl implements ReporEstoquePecaInsumoUseCase {

    private final PecaInsumoRepository pecaInsumoRepository;

    @Override
    @Transactional
    public void reporEstoque(List<DarBaixaEstoqueRequest> requests) {
        for (DarBaixaEstoqueRequest request : requests) {
            pecaInsumoRepository.incrementarEstoque(request.getItemId(), request.getQuantidade());
        }
    }
}
