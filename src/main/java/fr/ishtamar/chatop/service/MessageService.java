package fr.ishtamar.chatop.service;

import fr.ishtamar.chatop.dto.MessageDto;

public interface MessageService {

    /**
     * Saves message to database
     * @param message Message to be saved
     */
    void saveMessage(MessageDto message);
}
