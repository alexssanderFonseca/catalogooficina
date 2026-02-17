package br.com.oficina.catalogo.servico.core.usecase.impl;

import br.com.oficina.catalogo.servico.core.port.in.ListarServicosUseCase;
import br.com.oficina.catalogo.servico.core.port.out.ServicoRepository;
import br.com.oficina.catalogo.shared_kernel.domain.pagination.Page;
import br.com.oficina.catalogo.servico.core.domain.entity.Servico;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class ListarServicosUseCaseImpl implements ListarServicosUseCase {

    private final ServicoRepository servicoRepository;

    @Override
    public Page<Servico> executar(Long pagina, Long quantidade) {
        return servicoRepository.listar(pagina, quantidade);
    }
}
