package br.com.workmade.api.exceptionHandler;

import br.com.workmade.exceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface IApiExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
     ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e);

    @ExceptionHandler(NegocioException.class)
     ResponseEntity<?> tratarNegocioException(NegocioException e);

    @ExceptionHandler(EntidadeEmUsoException.class)
     ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException e);

    @ExceptionHandler(CozinhaNaoEncontradoException.class)
     ResponseEntity<?> tratarCozinhaNaoEncontradoException(CozinhaNaoEncontradoException e);

    @ExceptionHandler(EstadoNaoEncontradoException.class)
    ResponseEntity<?> tratarEstadoNaoEncontradoException(EstadoNaoEncontradoException e);

    @ExceptionHandler(RestauranteNaoEncontradoException.class)
    ResponseEntity<?> tratarRestauranteNaoEncontradoException(RestauranteNaoEncontradoException e);

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    ResponseEntity<?> tratarProdutoNaoEncontradoException(ProdutoNaoEncontradoException e);

}
