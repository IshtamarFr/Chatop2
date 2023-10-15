package fr.ishtamar.chatop.mapper;

import fr.ishtamar.chatop.dto.RentalDto;
import fr.ishtamar.chatop.entity.Rental;
import fr.ishtamar.chatop.service.UserInfoService;
import fr.ishtamar.chatop.service.impl.UserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {
    @Autowired
    private UserInfoService userInfoService=new UserInfoServiceImpl();

    public RentalDto toDto(Rental rental) {
        return RentalDto.builder()
                .id(rental.getId())
                .name(rental.getName())
                .created_at(rental.getCreated_at())
                .description(rental.getDescription())
                .owner_id(rental.getUser().getId())
                .price(rental.getPrice())
                .updated_at(rental.getUpdated_at())
                .picture(rental.getPicture())
                .surface(rental.getSurface())
                .build();
    }

    public Rental toEntity(RentalDto rental) {
        return Rental.builder()
                .name(rental.getName())
                .user(userInfoService.getUserById(rental.getOwner_id()))
                .surface(rental.getSurface())
                .price(rental.getPrice())
                .description(rental.getDescription())
                .build();
    }

}
