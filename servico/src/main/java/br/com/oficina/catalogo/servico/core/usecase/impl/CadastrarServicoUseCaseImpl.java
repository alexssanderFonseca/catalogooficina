package br.com.oficina.catalogo.servico.core.usecase.impl;

import br.com.oficina.catalogo.servico.core.domain.entity.Servico;
import br.com.oficina.catalogo.servico.core.port.in.CadastrarServicoUseCase;
import br.com.oficina.catalogo.servico.core.port.out.ServicoRepository;
import br.com.oficina.catalogo.servico.core.usecase.input.CadastrarServicoInput;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Named
@RequiredArgsConstructor
public class CadastrarServicoUseCaseImpl implements CadastrarServicoUseCase {

    private final ServicoRepository servicoRepository;

    @Override
    public UUID executar(CadastrarServicoInput input) {
        var servico = new Servico(
                UUID.randomUUID(),
                input.nome(),
                input.descricao(),
                input.preco(),
                input.duracaoEstimada(),
                input.categoria()
        );
        return servicoRepository.cadastrar(servico)
                .getId();
    }
}
