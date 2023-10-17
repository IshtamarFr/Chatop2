package fr.ishtamar.chatop.mapper;


import fr.ishtamar.chatop.dto.RentalDto;
import fr.ishtamar.chatop.entity.Rental;
import fr.ishtamar.chatop.service.UserInfoService;
import fr.ishtamar.chatop.service.impl.UserInfoServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "Spring")
public abstract class RentalMapper implements EntityMapper<RentalDto, Rental> {

    @Autowired
    UserInfoService userInfoService = new UserInfoServiceImpl();

    @Mappings({
            @Mapping(target = "user", expression = "java(this.userInfoService.getUserById(rentalDto.getOwner_id()))")
    })
    public abstract Rental toEntity(RentalDto rentalDto);

    @Mappings({
            @Mapping(source = "rental.user.id", target = "owner_id")
    })
    public abstract RentalDto toDto(Rental rental);
}
