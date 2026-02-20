package br.com.oficina.catalogo.peca_insumo.adapter.out.persistence.jpa;

import br.com.oficina.catalogo.peca_insumo.adapter.out.persistence.entity.PecaInsumoEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface PecaInsumoJpaRepository extends JpaRepository<PecaInsumoEntity, UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from PecaInsumoEntity p where p.id = :id")
    Optional<PecaInsumoEntity> findByIdWithLock(UUID id);

    @Modifying
    @Query("update PecaInsumoEntity p set p.quantidadeEstoque = p.quantidadeEstoque - :quantidade where p.id = :id and p.quantidadeEstoque >= :quantidade")
    int decrementStock(UUID id, Integer quantidade);

    @Modifying
    @Query("update PecaInsumoEntity p set p.quantidadeEstoque = p.quantidadeEstoque + :quantidade where p.id = :id")
    void incrementStock(UUID id, Integer quantidade);
}
