package br.com.oficina.catalogo.servico.core.usecase.input;

import java.math.BigDecimal;

public record CadastrarServicoInput(
        String nome,
        String descricao,
        BigDecimal preco,
        Integer duracaoEstimada,
        String categoria
) {
}
