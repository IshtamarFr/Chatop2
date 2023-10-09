package fr.ishtamar.chatop.dto;

import fr.ishtamar.chatop.entity.UserInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    @NotNull
    @Size(max=30)
    private String name;

    @NotNull
    @Size(max=63)
    @Email
    private String email;

    private Date created_at;
    private Date updated_at;

    public UserDto(UserInfo userInfo) {
        id= userInfo.getId();
        name= userInfo.getName();
        email = userInfo.getEmail();
    }
}
