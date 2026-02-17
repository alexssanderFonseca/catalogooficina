package br.com.oficina.catalogo.servico.core.usecase.impl;

import br.com.oficina.catalogo.servico.core.domain.entity.Servico;
import br.com.oficina.catalogo.servico.core.domain.exception.ServicoNaoEncontradoException;
import br.com.oficina.catalogo.servico.core.port.in.BuscarServicoPorIdUseCase;
import br.com.oficina.catalogo.servico.core.port.out.ServicoRepository;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Named
@RequiredArgsConstructor
public class BuscarServicoPorIdUseCaseImpl implements BuscarServicoPorIdUseCase {

    private final ServicoRepository servicoRepository;

    @Override
    public Servico executar(UUID id) {
        return servicoRepository.buscarPorId(id)
                .orElseThrow(() -> new ServicoNaoEncontradoException(id));
    }
}
