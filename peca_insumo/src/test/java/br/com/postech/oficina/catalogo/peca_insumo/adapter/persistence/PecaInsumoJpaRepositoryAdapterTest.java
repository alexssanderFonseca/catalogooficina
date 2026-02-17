package br.com.postech.oficina.catalogo.peca_insumo.adapter.persistence;

import br.com.oficina.catalogo.peca_insumo.adapter.out.persistence.PecaInsumoJpaRepositoryAdapter;
import br.com.oficina.catalogo.peca_insumo.adapter.out.persistence.entity.PecaInsumoEntity;
import br.com.oficina.catalogo.peca_insumo.adapter.out.persistence.jpa.PecaInsumoJpaRepository;
import br.com.oficina.catalogo.peca_insumo.adapter.out.persistence.mapper.PecaInsumoMapper;
import br.com.oficina.catalogo.peca_insumo.core.domain.entity.PecaInsumo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PecaInsumoJpaRepositoryAdapterTest {

    @Mock
    private PecaInsumoJpaRepository jpaRepository;

    @Mock
    private PecaInsumoMapper mapper;

    @InjectMocks
    private PecaInsumoJpaRepositoryAdapter adapter;

    private UUID itemId;
    private PecaInsumoEntity pecaInsumoEntity;
    private PecaInsumo pecaInsumoDomain;

    @BeforeEach
    void setUp() {
        itemId = UUID.randomUUID();
        pecaInsumoEntity = new PecaInsumoEntity();
        pecaInsumoEntity.setId(itemId);
        pecaInsumoEntity.setQuantidadeEstoque(100);

        pecaInsumoDomain = new PecaInsumo(itemId, "Peca Teste", "Desc", "COD1", "Marca", 100, null, null, "Categoria", true, null, null);
    }

    @Test
    void shouldFindByIdWithLockWhenEntityExists() {
        // Given
        when(jpaRepository.findByIdWithLock(itemId)).thenReturn(Optional.of(pecaInsumoEntity));
        when(mapper.toDomain(pecaInsumoEntity)).thenReturn(pecaInsumoDomain);

        // When
        Optional<PecaInsumo> result = adapter.buscarPorIdComBloqueio(itemId);

        // Then
        assertTrue(result.isPresent());
        assertEquals(pecaInsumoDomain, result.get());
        verify(jpaRepository, times(1)).findByIdWithLock(itemId);
        verify(mapper, times(1)).toDomain(pecaInsumoEntity);
    }

    @Test
    void shouldReturnEmptyOptionalWhenFindByIdWithLockNotFound() {
        // Given
        when(jpaRepository.findByIdWithLock(itemId)).thenReturn(Optional.empty());

        // When
        Optional<PecaInsumo> result = adapter.buscarPorIdComBloqueio(itemId);

        // Then
        assertFalse(result.isPresent());
        verify(jpaRepository, times(1)).findByIdWithLock(itemId);
        verify(mapper, never()).toDomain(any());
    }

    @Test
    void shouldDecrementStockSuccessfully() {
        // Given
        int quantityToDecrement = 10;
        when(jpaRepository.decrementStock(itemId, quantityToDecrement)).thenReturn(1); // 1 row affected

        // When
        boolean result = adapter.decrementarEstoque(itemId, quantityToDecrement);

        // Then
        assertTrue(result);
        verify(jpaRepository, times(1)).decrementStock(itemId, quantityToDecrement);
    }

    @Test
    void shouldReturnFalseWhenDecrementStockInsufficient() {
        // Given
        int quantityToDecrement = 10;
        when(jpaRepository.decrementStock(itemId, quantityToDecrement)).thenReturn(0); // 0 rows affected

        // When
        boolean result = adapter.decrementarEstoque(itemId, quantityToDecrement);

        // Then
        assertFalse(result);
        verify(jpaRepository, times(1)).decrementStock(itemId, quantityToDecrement);
    }
}
