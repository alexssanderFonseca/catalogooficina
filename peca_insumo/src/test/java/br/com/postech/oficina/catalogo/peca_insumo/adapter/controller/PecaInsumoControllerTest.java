package br.com.postech.oficina.catalogo.peca_insumo.adapter.controller;

import br.com.oficina.catalogo.peca_insumo.adapter.in.controller.PecaInsumoController;
import br.com.oficina.catalogo.peca_insumo.adapter.in.controller.PecaInsumoControllerMapper;
import br.com.oficina.catalogo.peca_insumo.adapter.in.controller.handler.PecaInsumoControllerAdvice;
import br.com.oficina.catalogo.peca_insumo.adapter.in.controller.request.DarBaixaEstoqueRequest;
import br.com.oficina.catalogo.peca_insumo.core.domain.exception.PecaInsumoIndisponivelException;
import br.com.oficina.catalogo.peca_insumo.core.port.in.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = {PecaInsumoController.class, PecaInsumoControllerAdvice.class}) // Include the advice
class PecaInsumoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean private DarBaixaEstoquePecaInsumoUseCase darBaixaEstoquePecaInsumoUseCase;
    @MockitoBean private CadastrarPecaInsumoUseCase cadastrarPecaInsumoUseCase;
    @MockitoBean private ListarPecasInsumoUseCase listarPecasInsumoUseCase;
    @MockitoBean private BuscarPecaInsumoPorIdUseCase buscarPecaInsumoPorIdUseCase;
    @MockitoBean private AtualizarPecaInsumoUseCase atualizarPecaInsumoUseCase;
    @MockitoBean private DeletarPecaInsumoUseCase deletarPecaInsumoUseCase;
    @MockitoBean private PecaInsumoControllerMapper mapper;


    private DarBaixaEstoqueRequest validRequest;
    private List<DarBaixaEstoqueRequest> validRequests;

    @BeforeEach
    void setUp() {
        validRequest = new DarBaixaEstoqueRequest(UUID.randomUUID(), 5);
        validRequests = Collections.singletonList(validRequest);
    }

    @Test
    void shouldReturn204WhenStockReductionIsSuccessful() throws Exception {
        // Given
        doNothing().when(darBaixaEstoquePecaInsumoUseCase).darBaixaEstoque(validRequests);

        // When & Then
        mockMvc.perform(put("/pecas/dar-baixa-estoque")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequests)))
                .andExpect(status().isNoContent());

        verify(darBaixaEstoquePecaInsumoUseCase, times(1)).darBaixaEstoque(validRequests);
    }

    @Test
    void shouldReturn422WhenStockIsInsufficient() throws Exception {
        // Given
        doThrow(new PecaInsumoIndisponivelException())
                .when(darBaixaEstoquePecaInsumoUseCase).darBaixaEstoque(anyList());

        // When & Then
        mockMvc.perform(put("/pecas/dar-baixa-estoque")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequests)))
                .andExpect(status().isUnprocessableEntity());

        verify(darBaixaEstoquePecaInsumoUseCase, times(1)).darBaixaEstoque(anyList());
    }

    @Test
    void shouldReturn400WhenRequestBodyIsInvalid() throws Exception {
        // Given - request with null item ID (invalid)
        List<DarBaixaEstoqueRequest> invalidRequests = List.of(new DarBaixaEstoqueRequest(null, 5));

        // When & Then
        mockMvc.perform(put("/pecas/dar-baixa-estoque")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequests)))
                .andExpect(status().isBadRequest());

        verify(darBaixaEstoquePecaInsumoUseCase, never()).darBaixaEstoque(anyList());
    }
}
