package by.vlad.liquibase_starter.mapper;

import by.vlad.liquibase_starter.dto.UserDto;
import by.vlad.liquibase_starter.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    UserDto EntityToDto(User user);
    User DtoToEntity(UserDto user);
}
