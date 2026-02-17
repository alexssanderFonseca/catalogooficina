package br.com.oficina.catalogo.servico.core.usecase.input;

import java.math.BigDecimal;
import java.util.UUID;

public record AtualizarServicoInput(
        UUID id,
        BigDecimal preco,
        boolean ativo
) {
}
