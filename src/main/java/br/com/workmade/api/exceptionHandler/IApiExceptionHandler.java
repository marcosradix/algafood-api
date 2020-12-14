package br.com.workmade.api.exceptionHandler;

import br.com.workmade.exceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

public interface IApiExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
     ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e, WebRequest wr);

    @ExceptionHandler(NegocioException.class)
     ResponseEntity<?> handleNegocioException(NegocioException e, WebRequest wr);

    @ExceptionHandler(EntidadeEmUsoException.class)
     ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest wr);

    @ExceptionHandler(CozinhaNaoEncontradoException.class)
     ResponseEntity<?> handleCozinhaNaoEncontradoException(CozinhaNaoEncontradoException e, WebRequest wr);

    @ExceptionHandler(EstadoNaoEncontradoException.class)
    ResponseEntity<?> handleEstadoNaoEncontradoException(EstadoNaoEncontradoException e, WebRequest wr);

    @ExceptionHandler(RestauranteNaoEncontradoException.class)
    ResponseEntity<?> handleRestauranteNaoEncontradoException(RestauranteNaoEncontradoException e, WebRequest wr);

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    ResponseEntity<?> handleProdutoNaoEncontradoException(ProdutoNaoEncontradoException e, WebRequest wr);

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, WebRequest wr);

}
