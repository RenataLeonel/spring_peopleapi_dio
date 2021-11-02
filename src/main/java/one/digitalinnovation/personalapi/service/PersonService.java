package one.digitalinnovation.personalapi.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personalapi.dto.request.PersonDTO;
import one.digitalinnovation.personalapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personalapi.entity.Person;
import one.digitalinnovation.personalapi.exception.PersonNotFoundException;
import one.digitalinnovation.personalapi.mapper.PersonMapper;
import one.digitalinnovation.personalapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service //Indica que essa classe será responsável por gerenciar as regras de negócio
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonRepository personRepository; //Declarar o PersonRepository

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    //Esse construtor é criado automaticamente pela anotação do lombok @AllArgsConstructor(onConstructor = @__(@Autowired))
    /*@Autowired //Injeção de dependência da interface PersonRepository
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }*/

    //Cria/cadastra uma pessoa
    public MessageResponseDTO createPerson(PersonDTO personDTO){ //Cria uma pessoa do tipo Persom
        Person personToSave = personMapper.toModel(personDTO); //Ao invés de conveter manualmente criamos o PersonMapper
        Person savedPerson = personRepository.save(personToSave); //Salva a pessoa
        return createMessageResponse(savedPerson.getId(), "Created person with ID");
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll(); // findAll = método padrão para listar tudo
        return allPeople.stream()
                .map(personMapper::toDTO) //transforma lista person allPeople em DTO
                .collect(Collectors.toList()); //Pega os dados alterados e cria uma nova lista
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isEmpty()){
            throw new PersonNotFoundException(id); //Trata com try cath ou ocoloca na assinatura do método
        }
        return personMapper.toDTO(optionalPerson.get());

        /* O Código abaixo é uma opção de refatoração deste método
        Person person = personRepository.findById(id)
            .orElseThrow(() -> new PersonNotFoundException(id));
        return personMapper.toDTO(person);
         */

        /* O código abaixo utiliza o método de verificação em método separado
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
        */
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id); //Chama o método que verifica se o id existe
        personRepository.deleteById(id);
    }

    //Update pessoa
    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id); //Verifica se o id existe
        Person personToUpdate = personMapper.toModel(personDTO); //Ao invés de conveter manualmente criamos o PersonMapper
        Person updatedPerson = personRepository.save(personToUpdate); //Salva a pessoa
        return createMessageResponse(updatedPerson.getId(), "Updated person with ID");
    }

    //Verifica se o id existe (separamos esse método de verificação pois ele pode ser chamado por mais de um método)
    private void verifyIfExists(Long id) throws PersonNotFoundException {
        personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id)); //lança a exceção caso não exista o id
    }

    //Como esse código é usado em createPerson e updateById, separamos para otimizar o código
    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
