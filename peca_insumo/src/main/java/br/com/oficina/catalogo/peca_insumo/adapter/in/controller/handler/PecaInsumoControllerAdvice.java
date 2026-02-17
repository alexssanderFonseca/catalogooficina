package br.com.oficina.catalogo.peca_insumo.adapter.in.controller.handler;

import br.com.oficina.catalogo.peca_insumo.core.domain.exception.PecaInsumoException;
import br.com.oficina.catalogo.peca_insumo.core.domain.exception.PecaInsumoIndisponivelException;
import br.com.oficina.catalogo.peca_insumo.core.domain.exception.PecaInsumoNaoEncontradaException;
import br.com.oficina.catalogo.peca_insumo.core.domain.exception.PecaInsumoBaixaEstoqueFalhaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PecaInsumoControllerAdvice {

    // Assuming ApiError record/class is defined elsewhere or will be defined here
    // For now, I will define a simple record for ApiError as it's missing in the provided context
    public record ApiError(int status, String message) {}

    @ExceptionHandler(PecaInsumoException.class)
    public ResponseEntity<ApiError> handleMyCustomException(PecaInsumoException ex) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PecaInsumoNaoEncontradaException.class)
    public ResponseEntity<ApiError> handleMyCustomException(PecaInsumoNaoEncontradaException ex) {
        ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PecaInsumoIndisponivelException.class)
    public ResponseEntity<ApiError> handleMyCustomException(PecaInsumoIndisponivelException ex) {
        ApiError error = new ApiError(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(PecaInsumoBaixaEstoqueFalhaException.class)
    public ResponseEntity<ApiError> handlePecaInsumoBaixaEstoqueFalhaException(PecaInsumoBaixaEstoqueFalhaException ex) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
