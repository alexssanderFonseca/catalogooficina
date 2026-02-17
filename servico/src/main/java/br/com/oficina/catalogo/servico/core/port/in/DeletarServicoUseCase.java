package br.com.oficina.catalogo.servico.core.port.in;

import java.util.UUID;

public interface DeletarServicoUseCase {
    void executar(UUID id);
}
