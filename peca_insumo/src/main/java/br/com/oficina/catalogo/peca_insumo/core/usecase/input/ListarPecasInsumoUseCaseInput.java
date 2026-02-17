package br.com.oficina.catalogo.peca_insumo.core.usecase.input;

public record ListarPecasInsumoUseCaseInput(
        Long pagina,
        Long quantidade
) {
}
