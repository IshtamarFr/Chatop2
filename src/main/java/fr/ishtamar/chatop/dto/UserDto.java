package fr.ishtamar.chatop.dto;

import fr.ishtamar.chatop.entity.UserInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
@Builder
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

    @CreatedDate
    private Date created_at=new Date();

    @UpdateTimestamp
    private Date updated_at;
}
