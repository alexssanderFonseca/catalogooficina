package br.com.oficina.catalogo.peca_insumo.core.port.in;

import br.com.oficina.catalogo.peca_insumo.core.usecase.input.CadastrarPecaInsumoInput;
import br.com.oficina.catalogo.peca_insumo.core.domain.entity.PecaInsumo;

public interface CadastrarPecaInsumoUseCase {
    PecaInsumo executar(CadastrarPecaInsumoInput input);
}
