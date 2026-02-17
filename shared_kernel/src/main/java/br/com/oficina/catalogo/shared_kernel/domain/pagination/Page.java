package br.com.oficina.catalogo.shared_kernel.domain.pagination;

import java.util.List;

public record Page<T>(
        List<T> conteudo,
        long totalPaginas,
        long totalElementos,
        int pagina
) {

}
