package fr.ishtamar.chatop.dto;

import fr.ishtamar.chatop.entity.Message;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Date created_at=new Date();
    private Date updated_at;

    public MessageDto(Message mess) {
        id=mess.getId();
        rental_id=mess.getRental().getId();
        user_id=mess.getUser().getId();
        message=mess.getMessage();
        created_at=mess.getCreated_at();
        updated_at=mess.getUpdated_at();
    }
}
