package br.com.oficina.catalogo.servico.adapters.in.controller;

import br.com.oficina.catalogo.servico.core.port.in.*;
import br.com.oficina.catalogo.servico.adapters.in.controller.mapper.ServicoRequestMapper;
import br.com.oficina.catalogo.servico.adapters.in.controller.request.AtualizarServicoRequest;
import br.com.oficina.catalogo.servico.adapters.in.controller.request.CadastrarServicoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/servicos")
@RequiredArgsConstructor
@Tag(name = "Serviços", description = "Gerenciamento de serviços")
public class ServicoController {

    private final CadastrarServicoUseCase cadastrarServicoUseCase;
    private final ListarServicosUseCase listarServicosUseCase;
    private final BuscarServicoPorIdUseCase buscarServicoPorIdUseCase;
    private final AtualizarServicoUseCase atualizarServicoUseCase;
    private final DeletarServicoUseCase deletarServicoUseCase;
    private final ServicoRequestMapper mapper;


    @Operation(summary = "Listar todos os serviços")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de serviços retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<?> listarTodos(@RequestParam(required = false, defaultValue = "0") Long pagina,
                                         @RequestParam(required = false, defaultValue = "10") Long quantidade) {

        var output = listarServicosUseCase.executar(pagina, quantidade);

        var response = output
                .conteudo()
                .stream()
                .map(mapper::toResponse)
                .toList();

        var data = Map.of("pagina", output.pagina(),
                "totalPaginas", output.totalPaginas(),
                "totalElementos", output.totalElementos(),
                "data", response);

        return ResponseEntity.ok(data);
    }

    @Operation(summary = "Buscar serviço por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço encontrado"),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable(name = "id") UUID id) {
        var servico = buscarServicoPorIdUseCase.executar(id);
        return ResponseEntity.ok(mapper.toResponse(servico));
    }

    @Operation(summary = "Cadastrar novo serviço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Serviço cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastrarServicoRequest request) {
        var input = mapper.toInput(request);
        var id = cadastrarServicoUseCase.executar(input);
        return ResponseEntity.created(URI.create("/servicos/" + id)).body(new IdResponse(id));
    }

    @Operation(summary = "Atualizar serviço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable(name = "id") UUID id, @RequestBody @Valid AtualizarServicoRequest request) {
        var input = mapper.toInput(id, request);
        atualizarServicoUseCase.executar(input);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Deletar serviço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Serviço deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable(name = "id")UUID id) {
        deletarServicoUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}