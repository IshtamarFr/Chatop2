package fr.ishtamar.chatop.dto;

import fr.ishtamar.chatop.entity.UserInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    @NonNull
    @Size(max=30)
    private String name;

    @NonNull
    @Size(max=63)
    @Email
    private String email;

    public UserDto(UserInfo userInfo) {
        id= userInfo.getId();
        name= userInfo.getName();
        email = userInfo.getEmail();
    }
}
