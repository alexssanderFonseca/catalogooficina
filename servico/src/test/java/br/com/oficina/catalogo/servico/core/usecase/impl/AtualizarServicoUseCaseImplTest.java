package br.com.oficina.catalogo.servico.core.usecase.impl;

import br.com.oficina.catalogo.servico.core.port.in.BuscarServicoPorIdUseCase;
import br.com.oficina.catalogo.servico.core.port.out.ServicoRepository;
import br.com.oficina.catalogo.servico.core.usecase.impl.AtualizarServicoUseCaseImpl;
import br.com.oficina.catalogo.servico.core.domain.entity.Servico;
import br.com.oficina.catalogo.servico.core.domain.exception.ServicoNaoEncontradoException;
import br.com.oficina.catalogo.servico.core.usecase.input.AtualizarServicoInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarServicoUseCaseImplTest {

    @Mock
    private BuscarServicoPorIdUseCase buscarServicoPorIdUseCase;

    @Mock
    private ServicoRepository servicoRepository;

    @InjectMocks
    private AtualizarServicoUseCaseImpl atualizarServicoUseCase;

    @Test
    void deveAtualizarServicoComSucesso() {
        // Arrange
        var servicoId = UUID.randomUUID();
        var input = new AtualizarServicoInput(servicoId, new BigDecimal("250.00"), false);
        var servicoExistente = new Servico(servicoId, "Alinhamento", "Desc", new BigDecimal("200.00"), 60, "Cat");

        when(buscarServicoPorIdUseCase.executar(servicoId)).thenReturn(servicoExistente);

        // Act
        atualizarServicoUseCase.executar(input);

        // Assert
        ArgumentCaptor<Servico> servicoCaptor = ArgumentCaptor.forClass(Servico.class);
        verify(servicoRepository, times(1)).cadastrar(servicoCaptor.capture());
        Servico servicoSalvo = servicoCaptor.getValue();

        assertEquals(new BigDecimal("250.00"), servicoSalvo.getPreco());
        assertEquals(false, servicoSalvo.getAtivo());
    }

    @Test
    void deveLancarExcecaoQuandoServicoNaoEncontradoParaAtualizar() {
        // Arrange
        var servicoId = UUID.randomUUID();
        var input = new AtualizarServicoInput(servicoId, new BigDecimal("250.00"), false);

        when(buscarServicoPorIdUseCase.executar(servicoId)).thenThrow(new ServicoNaoEncontradoException(servicoId));

        // Act & Assert
        assertThrows(ServicoNaoEncontradoException.class, () -> {
            atualizarServicoUseCase.executar(input);
        });

        verify(servicoRepository, never()).cadastrar(any(Servico.class));
    }
}
