package br.com.oficina.catalogo.servico.core.domain.exception;

import java.util.UUID;

public class ServicoNaoEncontradoException extends ServicoException {
    public ServicoNaoEncontradoException(UUID id) {
        super("Serviço com ID " + id + " não encontrado.");
    }
}
