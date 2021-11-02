package one.digitalinnovation.personalapi.controller;

import one.digitalinnovation.personalapi.dto.request.PersonDTO;
import one.digitalinnovation.personalapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personalapi.exception.PersonNotFoundException;
import one.digitalinnovation.personalapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private final PersonService personService; //Declarar o PersonRepository

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @PostMapping //Método post
    @ResponseStatus(HttpStatus.CREATED) //Spring
    //Cria uma pessoa do tipo PersonDTO, requestBody mapeia requisição e valid indica a necessidade de validação
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO){
        return personService.createPerson(personDTO);
    }

    @GetMapping //Método get para pegar toda a lista
    public List<PersonDTO> listAll(){
        return personService.listAll();
    }

    @GetMapping("/{id}") // Método get pora pegar perspn por ID
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException { //@PathVariable faz requisição por id
        return personService.findById(id);
    }

    @PutMapping("/{id}")//Método para atualizar por id
    public MessageResponseDTO updateById (@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException { //@PathVariable mapeia a requisição por id, @RequestBody passa o corpo da requisição
        return personService.updateById(id, personDTO); //retorna id, objeto
    }

    @DeleteMapping("/{id}") //Método para deleter por id
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException { //trata exceção caso o id não exista
        personService.delete(id);
    }
}
