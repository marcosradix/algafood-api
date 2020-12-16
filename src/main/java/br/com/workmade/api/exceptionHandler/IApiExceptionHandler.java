package br.com.workmade.api.exceptionHandler;

import br.com.workmade.exceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public interface IApiExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    ResponseEntity<?> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException e, WebRequest wr);

    @ExceptionHandler(NegocioException.class)
    ResponseEntity<?> handleNegocio(NegocioException e, WebRequest wr);

    @ExceptionHandler(EntidadeEmUsoException.class)
    ResponseEntity<?> handleEntidadeEmUso(EntidadeEmUsoException e, WebRequest wr);

    @ExceptionHandler(CozinhaNaoEncontradoException.class)
    ResponseEntity<?> handleCozinhaNaoEncontrado(CozinhaNaoEncontradoException e, WebRequest wr);

    @ExceptionHandler(EstadoNaoEncontradoException.class)
    ResponseEntity<?> handleEstadoNaoEncontrado(EstadoNaoEncontradoException e, WebRequest wr);

    @ExceptionHandler(RestauranteNaoEncontradoException.class)
    ResponseEntity<?> handleRestauranteNaoEncontrado(RestauranteNaoEncontradoException e, WebRequest wr);

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    ResponseEntity<?> handleProdutoNaoEncontrado(ProdutoNaoEncontradoException e, WebRequest wr);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request);


}
