package br.com.oficina.catalogo.peca_insumo.core.domain.exception;

import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class PecaInsumoBaixaEstoqueFalhaException extends RuntimeException {
    private final List<UUID> failedItems;

    public PecaInsumoBaixaEstoqueFalhaException(List<UUID> failedItems) {
        super("Falha ao dar baixa no estoque para os seguintes itens: " + failedItems.toString());
        this.failedItems = failedItems;
    }

    public PecaInsumoBaixaEstoqueFalhaException(String message, List<UUID> failedItems) {
        super(message);
        this.failedItems = failedItems;
    }
}
