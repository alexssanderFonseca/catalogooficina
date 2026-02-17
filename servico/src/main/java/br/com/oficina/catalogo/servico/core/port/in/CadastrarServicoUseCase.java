package br.com.oficina.catalogo.servico.core.port.in;

import br.com.oficina.catalogo.servico.core.usecase.input.CadastrarServicoInput;

import java.util.UUID;

public interface CadastrarServicoUseCase {
    UUID executar(CadastrarServicoInput input);
}
