package fr.ishtamar.chatop.mapper;


import fr.ishtamar.chatop.dto.MessageDto;
import fr.ishtamar.chatop.entity.Message;
import fr.ishtamar.chatop.service.RentalService;
import fr.ishtamar.chatop.service.UserInfoService;
import fr.ishtamar.chatop.service.impl.RentalServiceImpl;
import fr.ishtamar.chatop.service.impl.UserInfoServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "Spring")
public abstract class MessageMapper implements EntityMapper<MessageDto, Message> {

    @Autowired
    UserInfoService userInfoService = new UserInfoServiceImpl();
    @Autowired
    RentalService rentalService = new RentalServiceImpl();

    @Mappings({
            @Mapping(target = "user", expression = "java(this.userInfoService.getUserById(messageDto.getUser_id()))"),
            @Mapping(target = "rental", expression = "java(this.rentalService.getRentalById(messageDto.getRental_id()))")
    })
    public abstract Message toEntity(MessageDto messageDto);

    @Mappings({
            @Mapping(source = "message.user.id", target = "user_id"),
            @Mapping(source = "message.rental.id", target = "rental_id")
    })
    public abstract MessageDto toDto(Message message);
}
