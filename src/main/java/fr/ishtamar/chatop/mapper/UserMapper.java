package fr.ishtamar.chatop.mapper;


import fr.ishtamar.chatop.dto.UserDto;
import fr.ishtamar.chatop.entity.UserInfo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, UserInfo> {
}
