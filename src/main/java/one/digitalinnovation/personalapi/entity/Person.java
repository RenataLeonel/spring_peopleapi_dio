package one.digitalinnovation.personalapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity //Define essa classe como uma entidade do BD
@Data //Gera getters e setters
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id //Chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Gera id automaticamente de forma incremental como estratégia
    private Long id;

    @Column(nullable = false) //Define esse campo como uma coluna not null, ou seja, obrigatório
    private String firstName;

    @Column(nullable = false) //Define esse campo como uma coluna not null, ou seja, obrigatório
    private String lastName;

    @Column(nullable = false, unique = true) //Define esse campo como uma coluna not null de valor único, ou seja, obrigatório e não repetido
    private String cpf;

    private LocalDate birthDate;

    //Uma pessoa pode ter muitos telefones e a persintencia, merge e remoção será feita em cascata
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @Column(nullable = false) //Define esse campo como uma coluna not null, ou seja, obrigatório
    private List<Phone> phones; //Recebe lista de telefones
}
