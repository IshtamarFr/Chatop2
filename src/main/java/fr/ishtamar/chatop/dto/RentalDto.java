package fr.ishtamar.chatop.dto;

import fr.ishtamar.chatop.entity.Rental;
import lombok.Data;

import java.util.Date;

@Data
public class RentalDto {
    private Long id;

    private String name;
    private Long user_id;

    private float surface;
    private float price;

    private String picture;
    private String description;

    private Date created_at;
    private Date updated_at;

    public RentalDto(Rental rental) {
        id=rental.getId();
        name=rental.getName();
        user_id=rental.getUser().getId();
        surface= rental.getSurface();
        price=rental.getPrice();
        picture=rental.getPicture();
        description=rental.getDescription();
        created_at=rental.getCreated_at();
        updated_at=rental.getUpdated_at();
    }
}
