package one.digitalinnovation.personalapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personalapi.enums.PhoneType;

import javax.persistence.*;

@Entity //Define essa classe como uma entidade do BD
@Data //Insere getters e setters
@Builder
@AllArgsConstructor //Constructor com todos os argumentos
@NoArgsConstructor //Constructor vazio
public class Phone {

    @Id //Chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Gera id automaticamente de forma incremental como estratégia
    private Long id;

    @Enumerated(EnumType.STRING) //Configura esse campo para receber uma enumeração enum do tipo String
    @Column(nullable = false) //Define esse campo como uma coluna not null, ou seja, obrigatório
    private PhoneType type; //Enum - Definimos os tipos de telefones aceitos

    @Column(nullable = false) //Define esse campo como uma coluna not null, ou seja, obrigatório
    private String number;
}
