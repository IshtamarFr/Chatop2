package fr.ishtamar.chatop.controller;

import fr.ishtamar.chatop.dto.RentalDto;
import fr.ishtamar.chatop.entity.Rental;
import fr.ishtamar.chatop.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class RentalController {
    @Autowired
    private RentalService service;

    @Operation(summary = "gets lists of all rentals",responses={
            @ApiResponse(responseCode="200", description = "Rentals are displayed"),
            @ApiResponse(responseCode="403", description = "Access unauthorized")
    })
    @GetMapping("/rentals")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public HashMap<String, List<RentalDto>> getAllRentals() {
        HashMap<String, List<RentalDto>> map = new HashMap<>();
        map.put("rentals", service.getAllRentals());
        return map;
    }

    @Operation(summary = "gets lists of all rentals",responses={
            @ApiResponse(responseCode="200", description = "Rentals are displayed"),
            @ApiResponse(responseCode="403", description = "Access unauthorized")
    })
    @GetMapping("/rentals/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public RentalDto getRental(@PathVariable("id") final long id) {
        return service.getRentalById(id);
    }
}
