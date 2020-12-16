package br.com.workmade.api.exceptionHandler;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Field {

    private String name;
    private String userMessage;
}
