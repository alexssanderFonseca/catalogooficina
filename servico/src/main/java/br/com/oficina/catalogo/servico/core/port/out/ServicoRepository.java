package br.com.oficina.catalogo.servico.core.port.out;

import br.com.oficina.catalogo.servico.core.domain.entity.Servico;
import br.com.oficina.catalogo.shared_kernel.domain.pagination.Page;

import java.util.Optional;
import java.util.UUID;

public interface ServicoRepository {
    Servico cadastrar(Servico servico);

    Optional<Servico> buscarPorId(UUID id);

    Page<Servico> listar(Long pagina, Long quantidade);

    void atualizar(Servico servico);

    void deletar(UUID id);
}
