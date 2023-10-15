package fr.ishtamar.chatop.entity;

import fr.ishtamar.chatop.dto.UserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=30)
    @Column(unique = true)
    private String name;

    @NotNull
    @Size(max=63)
    @Column(unique = true)
    private String email;

    @NotNull
    @Size(max=60)
    private String password;

    @NotNull
    private String roles="ROLE_USER";

    @CreatedDate
    private Date created_at=new Date();

    @UpdateTimestamp
    private Date updated_at;
}
