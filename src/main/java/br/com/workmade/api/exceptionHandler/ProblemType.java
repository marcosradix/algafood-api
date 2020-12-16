package br.com.workmade.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    CORPO_MAL_FORMATADO("/erro-corpo-da-chamada", "Corpo da chamada formatado incorretamente"),
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
    ERRO_DE_PARSE_DE_DADOS("/erro-parse-de-dados", "Erro no parse de dados."),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro de url inválido"),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
    DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://algafood.com.br".concat(path);
        this.title = title;

    }
}
