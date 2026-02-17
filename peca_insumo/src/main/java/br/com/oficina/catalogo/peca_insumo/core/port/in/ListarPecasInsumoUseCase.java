package br.com.oficina.catalogo.peca_insumo.core.port.in;

import br.com.oficina.catalogo.peca_insumo.core.usecase.input.ListarPecasInsumoUseCaseInput;
import br.com.oficina.catalogo.peca_insumo.core.usecase.output.ListarPecasInsumoUseCaseOutput;
import br.com.oficina.catalogo.shared_kernel.domain.pagination.Page;

public interface ListarPecasInsumoUseCase {
    Page<ListarPecasInsumoUseCaseOutput> executar(ListarPecasInsumoUseCaseInput input);
}
