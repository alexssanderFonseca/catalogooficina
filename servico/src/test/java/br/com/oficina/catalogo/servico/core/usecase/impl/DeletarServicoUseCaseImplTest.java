package br.com.oficina.catalogo.servico.core.usecase.impl;


import br.com.oficina.catalogo.servico.core.domain.entity.Servico;
import br.com.oficina.catalogo.servico.core.domain.exception.ServicoNaoEncontradoException;
import br.com.oficina.catalogo.servico.core.port.in.BuscarServicoPorIdUseCase;
import br.com.oficina.catalogo.servico.core.port.out.ServicoRepository;
import br.com.oficina.catalogo.servico.core.usecase.impl.DeletarServicoUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeletarServicoUseCaseImplTest {

    @Mock
    private BuscarServicoPorIdUseCase buscarServicoPorIdUseCase;

    @Mock
    private ServicoRepository servicoRepository;

    @InjectMocks
    private DeletarServicoUseCaseImpl deletarServicoUseCase;

    @Test
    void deveInativarServicoComSucesso() {
        // Arrange
        var servicoId = UUID.randomUUID();
        var servicoExistente = new Servico(servicoId, "Alinhamento", "Desc", new BigDecimal("200.00"), 60, "Cat");
        assertTrue(servicoExistente.getAtivo()); // Garante que o estado inicial é ativo

        when(buscarServicoPorIdUseCase.executar(servicoId)).thenReturn(servicoExistente);

        // Act
        deletarServicoUseCase.executar(servicoId);

        // Assert
        ArgumentCaptor<Servico> servicoCaptor = ArgumentCaptor.forClass(Servico.class);
        verify(servicoRepository, times(1)).atualizar(servicoCaptor.capture());
        Servico servicoSalvo = servicoCaptor.getValue();

        assertFalse(servicoSalvo.getAtivo());
    }

    @Test
    void deveLancarExcecaoQuandoServicoNaoEncontradoParaDeletar() {
        // Arrange
        var servicoId = UUID.randomUUID();
        when(buscarServicoPorIdUseCase.executar(servicoId)).thenThrow(new ServicoNaoEncontradoException(servicoId));

        // Act & Assert
        assertThrows(ServicoNaoEncontradoException.class, () -> {
            deletarServicoUseCase.executar(servicoId);
        });

        verify(servicoRepository, never()).cadastrar(any(Servico.class));
    }
}
