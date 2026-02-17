package br.com.oficina.catalogo.servico.adapters.in.controller.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ServicoResponse(
        UUID id,
        String nome,
        String descricao,
        BigDecimal preco,
        Boolean ativo,
        LocalDateTime dataCadastro,
        LocalDateTime dataAtualizacao
) {
}
