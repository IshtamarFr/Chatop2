package fr.ishtamar.chatop.service;

import fr.ishtamar.chatop.dto.MessageDto;
import fr.ishtamar.chatop.entity.Message;
import fr.ishtamar.chatop.entity.Rental;
import fr.ishtamar.chatop.entity.UserInfo;
import fr.ishtamar.chatop.exceptionhandler.EntityNotFoundException;
import fr.ishtamar.chatop.repository.MessageRepository;
import fr.ishtamar.chatop.repository.RentalRepository;
import fr.ishtamar.chatop.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired private RentalRepository rentalRepository;
    @Autowired private UserInfoRepository userInfoRepository;

    public void saveMessage(MessageDto message) {
        Optional<UserInfo> user = userInfoRepository.findById(message.getUser_id());
        if (user.isPresent()) {
            Optional<Rental> rental = rentalRepository.findById(message.getRental_id());
            if (rental.isPresent()) {
                Message realMessage=new Message(message);
                realMessage.setUser(user.get());
                realMessage.setRental(rental.get());
                messageRepository.save(realMessage);
            } else {
                throw new EntityNotFoundException(Rental.class,"id",message.getRental_id().toString());
            }
        } else {
            throw new EntityNotFoundException(UserInfo.class,"id",message.getUser_id().toString());
        }
    }
}
