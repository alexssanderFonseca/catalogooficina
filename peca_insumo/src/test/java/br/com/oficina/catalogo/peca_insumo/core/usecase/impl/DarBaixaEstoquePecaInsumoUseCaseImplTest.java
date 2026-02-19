package br.com.oficina.catalogo.peca_insumo.core.usecase.impl;

import br.com.oficina.catalogo.peca_insumo.adapter.in.controller.request.DarBaixaEstoqueRequest;
import br.com.oficina.catalogo.peca_insumo.core.domain.exception.PecaInsumoBaixaEstoqueFalhaException;
import br.com.oficina.catalogo.peca_insumo.core.port.out.PecaInsumoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DarBaixaEstoquePecaInsumoUseCaseImplTest {

    @Mock
    private PecaInsumoRepository pecaInsumoRepository;

    @InjectMocks
    private DarBaixaEstoquePecaInsumoUseCaseImpl darBaixaEstoquePecaInsumoUseCase;

    private UUID itemId;
    private DarBaixaEstoqueRequest request;

    @BeforeEach
    void setUp() {
        itemId = UUID.randomUUID();
        request = new DarBaixaEstoqueRequest(itemId, 10);
    }

    @Test
    void shouldDecrementStockSuccessfully() {
        // Given
        when(pecaInsumoRepository.decrementarEstoque(itemId, 10)).thenReturn(true);
        List<DarBaixaEstoqueRequest> requests = Collections.singletonList(request);

        // When
        assertDoesNotThrow(() -> darBaixaEstoquePecaInsumoUseCase.darBaixaEstoque(requests));

        // Then
        verify(pecaInsumoRepository, times(1)).decrementarEstoque(itemId, 10);
    }

    @Test
    void shouldThrowExceptionWhenStockIsInsufficient() {
        // Given
        when(pecaInsumoRepository.decrementarEstoque(itemId, 10)).thenReturn(false);
        List<DarBaixaEstoqueRequest> requests = Collections.singletonList(request);

        // When & Then
        assertThrows(PecaInsumoBaixaEstoqueFalhaException.class, () ->
                darBaixaEstoquePecaInsumoUseCase.darBaixaEstoque(requests));
        verify(pecaInsumoRepository, times(1)).decrementarEstoque(itemId, 10);
    }

    @Test
    void shouldDecrementStockForMultipleItemsSuccessfully() {
        // Given
        UUID itemId2 = UUID.randomUUID();
        DarBaixaEstoqueRequest request2 = new DarBaixaEstoqueRequest(itemId2, 5);
        List<DarBaixaEstoqueRequest> requests = List.of(request, request2);

        when(pecaInsumoRepository.decrementarEstoque(itemId, 10)).thenReturn(true);
        when(pecaInsumoRepository.decrementarEstoque(itemId2, 5)).thenReturn(true);

        // When
        assertDoesNotThrow(() -> darBaixaEstoquePecaInsumoUseCase.darBaixaEstoque(requests));

        // Then
        verify(pecaInsumoRepository, times(1)).decrementarEstoque(itemId, 10);
        verify(pecaInsumoRepository, times(1)).decrementarEstoque(itemId2, 5);
    }

    @Test
    void shouldThrowExceptionAndStopProcessingForMultipleItemsWhenOneIsInsufficient() {
        // Given
        UUID itemId2 = UUID.randomUUID();
        DarBaixaEstoqueRequest request2 = new DarBaixaEstoqueRequest(itemId2, 5);
        List<DarBaixaEstoqueRequest> requests = List.of(request, request2);

        when(pecaInsumoRepository.decrementarEstoque(itemId, 10)).thenReturn(true);
        when(pecaInsumoRepository.decrementarEstoque(itemId2, 5)).thenReturn(false); // This one fails

        // When & Then
        assertThrows(PecaInsumoBaixaEstoqueFalhaException.class, () ->
                darBaixaEstoquePecaInsumoUseCase.darBaixaEstoque(requests));

        // Verify that the first item was processed, but the second failed
        verify(pecaInsumoRepository, times(1)).decrementarEstoque(itemId, 10);
        verify(pecaInsumoRepository, times(1)).decrementarEstoque(itemId2, 5);
    }
}
