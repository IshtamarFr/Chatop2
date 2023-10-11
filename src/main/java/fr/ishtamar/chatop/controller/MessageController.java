package fr.ishtamar.chatop.controller;

import fr.ishtamar.chatop.dto.MessageDto;
import fr.ishtamar.chatop.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    private MessageService service;

    @Operation(summary = "sends a message",responses={
            @ApiResponse(responseCode="200", description = "Successfully created new rental"),
            @ApiResponse(responseCode="403", description = "Access unauthorized"),
            @ApiResponse(responseCode="404", description = "User or rental not found")
    })
    @PostMapping("/messages")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String postNewMessage(@RequestBody MessageDto message) {
        service.saveMessage(message);
        return "Message sent with success";
    }
}
