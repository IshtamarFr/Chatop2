package fr.ishtamar.chatop.service.impl;

import fr.ishtamar.chatop.dto.MessageDto;
import fr.ishtamar.chatop.entity.Rental;
import fr.ishtamar.chatop.entity.UserInfo;
import fr.ishtamar.chatop.exceptionhandler.EntityNotFoundException;
import fr.ishtamar.chatop.mapper.MessageMapper;
import fr.ishtamar.chatop.repository.MessageRepository;
import fr.ishtamar.chatop.repository.RentalRepository;
import fr.ishtamar.chatop.repository.UserInfoRepository;
import fr.ishtamar.chatop.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public void saveMessage(MessageDto message) {
        userInfoRepository.findById(message.getUser_id()).
                orElseThrow(()->new EntityNotFoundException(UserInfo.class,"id",message.getUser_id().toString()));
        rentalRepository.findById(message.getRental_id())
                .orElseThrow(()->new EntityNotFoundException(Rental.class,"id",message.getRental_id().toString()));
        messageRepository.save(messageMapper.toEntity(message));
    }
}
