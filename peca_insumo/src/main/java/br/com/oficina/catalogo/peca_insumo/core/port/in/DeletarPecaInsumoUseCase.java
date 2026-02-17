package br.com.oficina.catalogo.peca_insumo.core.port.in;

import java.util.UUID;

public interface DeletarPecaInsumoUseCase {

    void executar(UUID id);
}

