package br.com.workmade.api.exceptionHandler;

import br.com.workmade.exceptions.*;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.workmade.utils.Utils.createProblemBuilder;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler implements IApiExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);
        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
        }
        ProblemType problemType = ProblemType.CORPO_MAL_FORMATADO;
        String detail = "O corpo da requisição está inválido, pode ser um erro de sintaxe";
        Problem problem = createProblemBuilder(problemType, status, detail).build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String path = joinPath(ex.getPath());
        String detail = String.format("A propriedade '%s' recebeu o valor '%s' "
                .concat("que é de um tipo inválido. ")
                .concat("Corrija e informe um valor compatível com o tipo %s."), path, ex.getValue(), ex.getTargetType().getSimpleName());
        Problem problem = createProblemBuilder(ProblemType.CORPO_MAL_FORMATADO, status, detail).build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    public ResponseEntity<?> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException e, WebRequest wr) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        Problem problem = createProblemBuilder(ProblemType.ENTIDADE_NAO_ENCONTRADA, status, e.getMessage()).build();
        return handleExceptionInternal(e, problem, new HttpHeaders(), status, wr);
    }

    @Override
    public ResponseEntity<?> handleNegocio(NegocioException e, WebRequest wr) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        String detail = e.getMessage();
        Problem problem = createProblemBuilder(problemType, status, detail).build();
        return handleExceptionInternal(e, problem, new HttpHeaders(), status, wr);
    }

    @Override
    public ResponseEntity<?> handleEntidadeEmUso(EntidadeEmUsoException e, WebRequest wr) {
        HttpStatus status = HttpStatus.CONFLICT;
        Problem problem = createProblemBuilder(ProblemType.ENTIDADE_EM_USO, status, e.getMessage()).build();
        return handleExceptionInternal(e, problem, new HttpHeaders(), status, wr);

    }

    @Override
    public ResponseEntity<?> handleCozinhaNaoEncontrado(CozinhaNaoEncontradoException e, WebRequest wr) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        Problem problem = createProblemBuilder(ProblemType.RECURSO_NAO_ENCONTRADO, status, e.getMessage()).build();
        return handleExceptionInternal(e, problem, new HttpHeaders(), status, wr);
    }

    @Override
    public ResponseEntity<?> handleEstadoNaoEncontrado(EstadoNaoEncontradoException e, WebRequest wr) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        Problem problem = createProblemBuilder(ProblemType.RECURSO_NAO_ENCONTRADO, status, e.getMessage()).build();
        return handleExceptionInternal(e, problem, new HttpHeaders(), status, wr);
    }

    @Override
    public ResponseEntity<?> handleRestauranteNaoEncontrado(RestauranteNaoEncontradoException e, WebRequest wr) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        Problem problem = createProblemBuilder(ProblemType.RECURSO_NAO_ENCONTRADO, status, e.getMessage()).build();
        return handleExceptionInternal(e, problem, new HttpHeaders(), status, wr);
    }

    @Override
    public ResponseEntity<?> handleProdutoNaoEncontrado(ProdutoNaoEncontradoException e, WebRequest wr) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        Problem problem = createProblemBuilder(ProblemType.RECURSO_NAO_ENCONTRADO, status, e.getMessage()).build();
        return handleExceptionInternal(e, problem, new HttpHeaders(), status, wr);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, HttpHeaders headers,
                                                                    HttpStatus status, WebRequest request) {

        ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;

        String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', "
                        + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
                ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

        Problem problem = createProblemBuilder(problemType, status, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        String detail = String.format("O recurso '%s', que você tentou acessar, é inexistente.",
                ex.getRequestURL());

        Problem problem = createProblemBuilder(problemType, status, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            body = Problem.builder()
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .build();

        } else if (body instanceof String) {
            body = Problem.builder()
                    .title((String) body)
                    .status(status.value())
                    .build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(
            PropertyBindingException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        String path = joinPath(ex.getPath());
        ProblemType problemType = ProblemType.CORPO_MAL_FORMATADO;
        String detail = String.format("A propriedade '%s' não existe. "
                + "Corrija ou remova essa propriedade e tente novamente.", path);
        Problem problem = createProblemBuilder(problemType, status, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));
    }
}
