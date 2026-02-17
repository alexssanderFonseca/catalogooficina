package br.com.oficina.catalogo.servico.adapters.in.controller.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record AtualizarServicoRequest(@NotNull @Positive BigDecimal preco,
                                      @NotNull boolean ativo) {
}
