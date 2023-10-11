package fr.ishtamar.chatop.entity;

import fr.ishtamar.chatop.dto.MessageDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rental_id", referencedColumnName = "id")
    private Rental rental;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserInfo user;

    @NotNull
    private String message;

    @CreatedDate
    @Column(updatable = false)
    private Date created_at=new Date();

    @UpdateTimestamp
    private Date updated_at;

    public Message(MessageDto mess) {
        message=mess.getMessage();
    }
}