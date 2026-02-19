package br.com.oficina.catalogo.servico.core.usecase.impl;


import br.com.oficina.catalogo.servico.core.domain.entity.Servico;
import br.com.oficina.catalogo.servico.core.port.out.ServicoRepository;
import br.com.oficina.catalogo.servico.core.usecase.impl.ListarServicosUseCaseImpl;
import br.com.oficina.catalogo.shared_kernel.domain.pagination.Page;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarServicosUseCaseImplTest {

    @Mock
    private ServicoRepository servicoRepository;

    @InjectMocks
    private ListarServicosUseCaseImpl listarServicosUseCase;

    @Test
    void deveRetornarListaDeServicosQuandoExistirem() {
        // Arrange
        var servico1 = new Servico(UUID.randomUUID(), "Troca de Óleo", "Troca de óleo do motor", new BigDecimal("100.00"), 30, "Manutenção");
        var servico2 = new Servico(UUID.randomUUID(), "Alinhamento", "Alinhamento e balanceamento", new BigDecimal("150.00"), 60, "Manutenção");
        var servicosEsperados = List.of(servico1, servico2);

        when(servicoRepository.listar(0L, 10L)).thenReturn(new br.com.oficina.catalogo.shared_kernel.domain.pagination.Page<>(servicosEsperados, 1L, servicosEsperados.size(), 0));

        // Act
        Page<Servico> servicosAtuais = listarServicosUseCase.executar(0L, 10L);

        // Assert
        assertNotNull(servicosAtuais);
        assertFalse(servicosAtuais.conteudo().isEmpty());
        assertEquals(2, servicosAtuais.conteudo().size());
        assertEquals(servicosEsperados, servicosAtuais.conteudo());
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoExistiremServicos() {
        // Arrange
        when(servicoRepository.listar(0L, 10L)).thenReturn(new br.com.oficina.catalogo.shared_kernel.domain.pagination.Page<>(Collections.emptyList(), 0L, 0, 0));

        // Act
        Page<Servico> servicosAtuais = listarServicosUseCase.executar(0L, 10L);

        // Assert
        assertNotNull(servicosAtuais);
        assertTrue(servicosAtuais.conteudo().isEmpty());
    }
}
