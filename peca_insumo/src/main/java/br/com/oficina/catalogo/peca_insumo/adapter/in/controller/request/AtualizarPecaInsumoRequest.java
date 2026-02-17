package br.com.oficina.catalogo.peca_insumo.adapter.in.controller.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record AtualizarPecaInsumoRequest(@NotNull @Positive Integer qtd,
                                         @NotNull @Positive BigDecimal precoCusto,
                                         @NotNull @Positive BigDecimal precoVenda,
                                         @NotNull Boolean ativa) {
}
