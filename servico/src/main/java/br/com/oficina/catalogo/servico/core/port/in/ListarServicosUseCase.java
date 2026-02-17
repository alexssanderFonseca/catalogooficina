package br.com.oficina.catalogo.servico.core.port.in;

import br.com.oficina.catalogo.shared_kernel.domain.pagination.Page;
import br.com.oficina.catalogo.servico.core.domain.entity.Servico;

public interface ListarServicosUseCase {
    Page<Servico> executar(Long pagina, Long quantidade);
}
