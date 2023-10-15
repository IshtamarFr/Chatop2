package fr.ishtamar.chatop.mapper;

import fr.ishtamar.chatop.dto.MessageDto;
import fr.ishtamar.chatop.entity.Message;
import fr.ishtamar.chatop.service.RentalService;
import fr.ishtamar.chatop.service.UserInfoService;
import fr.ishtamar.chatop.service.impl.RentalServiceImpl;
import fr.ishtamar.chatop.service.impl.UserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {
    @Autowired
    private UserInfoService userInfoService = new UserInfoServiceImpl();
    @Autowired
    private RentalService rentalService = new RentalServiceImpl();

    public MessageDto toDto(Message message) {
        return MessageDto.builder()
                .message(message.getMessage())
                .id(message.getId())
                .created_at(message.getCreated_at())
                .rental_id(message.getRental().getId())
                .updated_at(message.getUpdated_at())
                .user_id(message.getUser().getId())
                .build();
    }

    public Message toEntity(MessageDto message) {
        return Message.builder()
                .message(message.getMessage())
                .user(userInfoService.getUserById(message.getUser_id()))
                .rental(rentalService.getRentalById(message.getRental_id()))
                .created_at(message.getCreated_at())
                .build();
    }
}
