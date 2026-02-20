package br.com.oficina.catalogo.peca_insumo.core.port.out;

import br.com.oficina.catalogo.peca_insumo.core.domain.entity.PecaInsumo;
import br.com.oficina.catalogo.shared_kernel.domain.pagination.Page;

import java.util.Optional;
import java.util.UUID;

public interface PecaInsumoRepository {
    PecaInsumo cadastrar(PecaInsumo pecaInsumo);

    Page<PecaInsumo> listar(Long pagina, Long quantidade);

    Optional<PecaInsumo> buscarPorId(UUID id);

    Optional<PecaInsumo> buscarPorIdComBloqueio(UUID id);

    boolean decrementarEstoque(UUID id, Integer quantidade);

    void incrementarEstoque(UUID id, Integer quantidade);

    void atualizar(PecaInsumo pecaInsumo);

    void deletar(UUID id);
}
