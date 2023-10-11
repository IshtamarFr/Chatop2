package fr.ishtamar.chatop.controller;

import fr.ishtamar.chatop.dto.RentalDto;
import fr.ishtamar.chatop.entity.Rental;
import fr.ishtamar.chatop.entity.UserInfo;
import fr.ishtamar.chatop.service.JwtService;
import fr.ishtamar.chatop.service.RentalService;
import fr.ishtamar.chatop.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@RestController
public class RentalController {
    @Autowired
    private RentalService service;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserInfoService userInfoService;

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

    @Operation(summary = "create a new rental",responses={
            @ApiResponse(responseCode="200", description = "Successfully created new rental"),
            @ApiResponse(responseCode="403", description = "Access unauthorized")
    })
    @PostMapping("/rentals")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public RentalDto createRental(
            //@formatter:off
            @RequestPart("picture") MultipartFile multipartFile,
            @RequestParam("name") String name,
            @RequestParam("surface") float surface,
            @RequestParam("price") float price,
            @RequestParam("description") String description,
            @RequestHeader("Authorization") String jwt
            //@formatter:on
    ) throws Exception {
        String username = jwtService.extractUsername(jwt.substring(7));
        Rental candidate = new Rental();
        candidate.setUser(userInfoService.getUserByUsername(username));
        candidate.setName(name);
        candidate.setSurface(surface);
        candidate.setPrice(price);
        candidate.setDescription(description);
        candidate.setPicture(service.savePicture(multipartFile));
        return service.saveRental(candidate);
    }
}
