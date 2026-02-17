package br.com.oficina.catalogo.servico.core.port.in;

import br.com.oficina.catalogo.servico.core.domain.entity.Servico;

import java.util.UUID;

public interface BuscarServicoPorIdUseCase {
    Servico executar(UUID id);
}
