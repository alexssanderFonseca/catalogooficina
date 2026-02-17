package br.com.oficina.catalogo.peca_insumo.core.usecase.impl;

import br.com.oficina.catalogo.peca_insumo.core.port.in.ListarPecasInsumoUseCase;
import br.com.oficina.catalogo.peca_insumo.core.port.out.PecaInsumoRepository;
import br.com.oficina.catalogo.peca_insumo.core.usecase.input.ListarPecasInsumoUseCaseInput;
import br.com.oficina.catalogo.peca_insumo.core.usecase.output.ListarPecasInsumoUseCaseOutput;
import br.com.oficina.catalogo.shared_kernel.domain.pagination.Page;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class ListarPecasInsumoUseCaseImpl implements ListarPecasInsumoUseCase {

    private final PecaInsumoRepository pecaInsumoRepository;

    @Override
    public Page<ListarPecasInsumoUseCaseOutput> executar(ListarPecasInsumoUseCaseInput input) {
        var pecaInsumosPage = pecaInsumoRepository.listar(input.pagina(), input.quantidade());
        var output = pecaInsumosPage.conteudo()
                .stream()
                .map(pecaInsumo -> new ListarPecasInsumoUseCaseOutput(
                        pecaInsumo.getId(),
                        pecaInsumo.getNome(),
                        pecaInsumo.getDescricao(),
                        pecaInsumo.getCodigoFabricante(),
                        pecaInsumo.getMarca(),
                        pecaInsumo.getQuantidadeEstoque(),
                        pecaInsumo.getPrecoCusto(),
                        pecaInsumo.getPrecoVenda(),
                        pecaInsumo.getCategoria(),
                        pecaInsumo.getAtivo(),
                        pecaInsumo.getDataCadastro(),
                        pecaInsumo.getDataAtualizacao()
                )).toList();

        return new Page<>(output,
                pecaInsumosPage.totalPaginas(),
                pecaInsumosPage.totalElementos(),
                pecaInsumosPage.pagina());
    }
}
