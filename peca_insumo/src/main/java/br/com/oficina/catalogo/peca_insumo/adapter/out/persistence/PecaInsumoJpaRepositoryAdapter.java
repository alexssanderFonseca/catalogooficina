package br.com.oficina.catalogo.peca_insumo.adapter.out.persistence;

import br.com.oficina.catalogo.peca_insumo.core.domain.entity.PecaInsumo;
import br.com.oficina.catalogo.peca_insumo.core.port.out.PecaInsumoRepository;
import br.com.oficina.catalogo.shared_kernel.domain.pagination.Page;
import br.com.oficina.catalogo.peca_insumo.adapter.out.persistence.jpa.PecaInsumoJpaRepository;
import br.com.oficina.catalogo.peca_insumo.adapter.out.persistence.mapper.PecaInsumoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PecaInsumoJpaRepositoryAdapter implements PecaInsumoRepository {

    private final PecaInsumoJpaRepository repository;
    private final PecaInsumoMapper mapper;

    @Override
    public PecaInsumo cadastrar(PecaInsumo pecaInsumo) {
        var entity = mapper.toEntity(pecaInsumo);
        var savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Page<PecaInsumo> listar(Long pagina, Long quantidade) {
        var pagePecaInsumo = repository
                .findAll(PageRequest.of(pagina.intValue(), quantidade.intValue()));

        var content = pagePecaInsumo.stream()
                .map(mapper::toDomain)
                .toList();

        return new Page<>(content,
                pagePecaInsumo.getTotalPages(),
                pagePecaInsumo.getTotalElements(),
                pagePecaInsumo.getNumber());
    }

    @Override
    public Optional<PecaInsumo> buscarPorId(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<PecaInsumo> buscarPorIdComBloqueio(UUID id) {
        return repository.findByIdWithLock(id).map(mapper::toDomain);
    }

    @Override
    public boolean decrementarEstoque(UUID id, Integer quantidade) {
        return repository.decrementStock(id, quantidade) > 0;
    }

    @Override
    public void incrementarEstoque(UUID id, Integer quantidade) {
        repository.incrementStock(id, quantidade);
    }

    @Override
    public void atualizar(PecaInsumo pecaInsumo) {
        var entity = mapper.toEntity(pecaInsumo);
        repository.save(entity);
    }

    @Override
    public void deletar(UUID id) {
        repository.deleteById(id);
    }
}
