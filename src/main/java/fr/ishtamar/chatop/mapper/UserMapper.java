package fr.ishtamar.chatop.mapper;

import fr.ishtamar.chatop.dto.UserDto;
import fr.ishtamar.chatop.entity.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(UserInfo user) {
       return UserDto.builder()
               .name(user.getName())
               .id(user.getId())
               .email(user.getEmail())
               .created_at(user.getCreated_at())
               .updated_at(user.getUpdated_at())
               .build();
    }

    public UserInfo toEntity(UserDto user) {
        return UserInfo.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
