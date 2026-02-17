package br.com.oficina.catalogo.servico.core.port.in;

import br.com.oficina.catalogo.servico.core.usecase.input.AtualizarServicoInput;

public interface AtualizarServicoUseCase {
    void executar(AtualizarServicoInput input);
}
