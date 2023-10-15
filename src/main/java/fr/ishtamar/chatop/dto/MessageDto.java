package fr.ishtamar.chatop.dto;

import fr.ishtamar.chatop.entity.Message;
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
public class MessageDto {
    private Long id;

    @NotNull
    private Long rental_id;

    @NotNull
    private Long user_id;

    @NotNull
    @Size(max=2000)
    private String message;

    @CreatedDate
    private Date created_at=new Date();

    @UpdateTimestamp
    private Date updated_at;
}
