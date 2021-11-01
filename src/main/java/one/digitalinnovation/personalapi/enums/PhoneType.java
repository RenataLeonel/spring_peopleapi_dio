package one.digitalinnovation.personalapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter //Cria getters
@AllArgsConstructor //Cria construtores
public enum PhoneType {

    //Tipos de telefones aceitos
    HOME ("Home"),
    MOBILE ("Mobile"),
    COMMERCIAL ("Commercial");

    private final String description;
}
