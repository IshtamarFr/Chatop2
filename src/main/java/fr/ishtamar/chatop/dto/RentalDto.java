package fr.ishtamar.chatop.dto;

import jakarta.validation.constraints.Min;
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

    @CreatedDate
    private Date created_at = new Date();

    @UpdateTimestamp
    private Date updated_at;
}
