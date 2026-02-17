package br.com.oficina.catalogo.peca_insumo.core.domain.exception;

public class PecaInsumoIndisponivelException extends PecaInsumoException {
    public PecaInsumoIndisponivelException() {
        super("Item não disponivel em estoque");
    }
}
