package br.com.oficina.catalogo.servico.adapters.out.persistence;

import br.com.oficina.catalogo.servico.adapters.out.persistence.entity.ServicoJpaRepository;
import br.com.oficina.catalogo.servico.adapters.out.persistence.mapper.ServicoRepositoryMapper;
import br.com.oficina.catalogo.servico.core.domain.entity.Servico;
import br.com.oficina.catalogo.servico.core.port.out.ServicoRepository;
import br.com.oficina.catalogo.shared_kernel.domain.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ServicoJpaRepositoryAdapter implements ServicoRepository {

    private final ServicoJpaRepository repository;
    private final ServicoRepositoryMapper mapper;

    @Override
    public Servico cadastrar(Servico servico) {
        var entity = mapper.toEntity(servico);
        var savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Servico> buscarPorId(UUID id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Servico> listar(Long pagina, Long quantidade) {
        var pageServico = repository.findAll(PageRequest.of(pagina.intValue(), quantidade.intValue()));
        var content = pageServico.getContent().stream()
                .map(mapper::toDomain)
                .toList();

        return new Page<>(content,
                pageServico.getTotalPages(),
                pageServico.getTotalElements(),
                pageServico.getNumber());
    }

    @Override
    public void atualizar(Servico servico) {
        var entity = mapper.toEntity(servico);
        repository.save(entity);
    }

    @Override
    public void deletar(UUID id) {
        repository.deleteById(id);
    }
}
