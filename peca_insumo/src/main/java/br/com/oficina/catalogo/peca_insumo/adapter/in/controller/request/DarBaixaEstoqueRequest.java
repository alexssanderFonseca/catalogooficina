package br.com.oficina.catalogo.peca_insumo.adapter.in.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class DarBaixaEstoqueRequest {
    @NotNull(message = "O ID do item não pode ser nulo")
    private UUID itemId;
    @NotNull(message = "A quantidade não pode ser nula")
    @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
    private Integer quantidade;
}
