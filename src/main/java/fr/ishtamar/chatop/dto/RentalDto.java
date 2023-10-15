package fr.ishtamar.chatop.dto;

import fr.ishtamar.chatop.entity.Rental;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class RentalDto {
    private Long id;

    @NotNull
    @Size(max=63)
    private String name;

    @NotNull
    private Long owner_id;

    @NotNull
    @Min(value=0)
    private float surface;

    @NotNull
    @Min(value=0)
    private float price;

    private String picture;

    @Size(max=2000)
    private String description;

    private Date created_at;
    private Date updated_at;
}
