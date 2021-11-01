package one.digitalinnovation.personalapi.mapper;

import one.digitalinnovation.personalapi.dto.request.PersonDTO;
import one.digitalinnovation.personalapi.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper //Mapeia e converte DTO em entidade
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target ="birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy") //Formata a data , tranformando string em date
    Person toModel(PersonDTO personDTO);//DTO para model

    PersonDTO toDTO(Person person);//Model para DTO
}
