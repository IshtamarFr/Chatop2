package fr.ishtamar.chatop.entity;

import fr.ishtamar.chatop.dto.RentalDto;
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
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=63)
    private String name;

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private UserInfo user;

    @NotNull
    private float surface;
    @NotNull
    private float price;

    private String picture;
    private String description;

    @CreatedDate
    @Column(updatable = false)
    private Date created_at=new Date();

    @UpdateTimestamp
    private Date updated_at;

    public Rental(RentalDto rentalDto){
        name=rentalDto.getName();
        surface=rentalDto.getSurface();
        price=rentalDto.getPrice();
        description=rentalDto.getDescription();
    }
}
