package br.com.oficina.catalogo.peca_insumo.core.usecase.impl;

import br.com.oficina.catalogo.peca_insumo.adapter.in.controller.request.DarBaixaEstoqueRequest;
import br.com.oficina.catalogo.peca_insumo.core.domain.exception.PecaInsumoBaixaEstoqueFalhaException;
import br.com.oficina.catalogo.peca_insumo.core.port.in.DarBaixaEstoquePecaInsumoUseCase;
import br.com.oficina.catalogo.peca_insumo.core.port.out.PecaInsumoRepository;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Named
@RequiredArgsConstructor
public class DarBaixaEstoquePecaInsumoUseCaseImpl implements DarBaixaEstoquePecaInsumoUseCase {

    private final PecaInsumoRepository pecaInsumoRepository;

    @Override
    @Transactional
    public void darBaixaEstoque(List<DarBaixaEstoqueRequest> requests) {
        List<UUID> failedItems = new ArrayList<>();
        for (DarBaixaEstoqueRequest request : requests) {
            boolean success = pecaInsumoRepository.decrementarEstoque(request.getItemId(), request.getQuantidade());
            if (!success) {
                failedItems.add(request.getItemId());
            }
        }

        if (!failedItems.isEmpty()) {
            throw new PecaInsumoBaixaEstoqueFalhaException(failedItems);
        }
    }
}
