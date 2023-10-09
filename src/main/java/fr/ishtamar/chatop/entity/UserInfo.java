package fr.ishtamar.chatop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
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

    private String roles;
}
