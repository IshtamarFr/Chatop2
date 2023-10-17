package fr.ishtamar.chatop.controller;

import fr.ishtamar.chatop.dto.RentalDto;
import fr.ishtamar.chatop.entity.Rental;
import fr.ishtamar.chatop.entity.UserInfo;
import fr.ishtamar.chatop.exceptionhandler.OwnerMismatchException;
import fr.ishtamar.chatop.mapper.RentalMapper;
import fr.ishtamar.chatop.service.JwtService;
import fr.ishtamar.chatop.service.RentalService;
import fr.ishtamar.chatop.service.UserInfoService;
import fr.ishtamar.chatop.service.impl.RentalServiceImpl;
import fr.ishtamar.chatop.service.impl.UserInfoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@RestController
@Validated
public class RentalController {
    @Autowired
    private RentalService service=new RentalServiceImpl();
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserInfoService userInfoService=new UserInfoServiceImpl();
    @Autowired
    private RentalMapper rentalMapper;

    @Operation(summary = "gets lists of all rentals",responses={
            @ApiResponse(responseCode="200", description = "Rentals are displayed"),
            @ApiResponse(responseCode="403", description = "Access unauthorized")
    })
    @GetMapping("/rentals")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public HashMap<String, List<RentalDto>> getAllRentals() {
        HashMap<String, List<RentalDto>> map = new HashMap<>();
        map.put("rentals", service.getAllRentals().stream().map(rental -> rentalMapper.toDto(rental)).toList());
        return map;
    }

    @Operation(summary = "gets chosen rental",responses={
            @ApiResponse(responseCode="200", description = "Rentals are displayed"),
            @ApiResponse(responseCode="403", description = "Access unauthorized"),
            @ApiResponse(responseCode="404", description = "Rental not found")
    })
    @GetMapping("/rentals/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public RentalDto getRental(@PathVariable("id") final long id) {
        return rentalMapper.toDto(service.getRentalById(id));
    }

    @Operation(summary = "create a new rental",responses={
            @ApiResponse(responseCode="200", description = "Successfully created new rental"),
            @ApiResponse(responseCode="403", description = "Access unauthorized")
    })
    @PostMapping(value = "/rentals",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public RentalDto createRental(
            //@formatter:off
            @RequestPart("picture") MultipartFile multipartFile,
            @RequestParam("name") @NotBlank @Size(max=63) String name,
            @RequestParam("surface") @Min(0) float surface,
            @RequestParam("price") @Min(0) float price,
            @RequestParam("description") @Size(max=2000) String description,
            @RequestHeader(value="Authorization",required = false) String jwt
            //@formatter:on
    ) throws Exception {
        String username = jwtService.extractUsername(jwt.substring(7));
        Rental candidate = Rental.builder()
            .user(userInfoService.getUserByUsername(username))
            .name(name)
            .surface(surface)
            .price(price)
            .description(description)
            .picture(service.savePicture(multipartFile))
            .build();
        return rentalMapper.toDto(service.saveRental(candidate));
    }

    @Operation(summary = "modify owner's rental",responses={
            @ApiResponse(responseCode="200", description = "Successfully created new rental"),
            @ApiResponse(responseCode="403", description = "Access unauthorized"),
            @ApiResponse(responseCode="400", description="Incorrect user")
    })
    @PutMapping("/rentals/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    //@formatter:off
    /*
     * IMPORTANT :
     * 1- this PutMapping gets PathVariable for Rental's id
     * 2- Owner's id must be gotten from Jwt and checked against expected owner's id
     * 3- This method doesn't allow to change picture (not a requested feature)
     */
    //@formatter: on
    public RentalDto modifyRental(
            //@formatter: off
            @RequestParam("name") @NotBlank @Size(max=63) String name,
            @RequestParam("surface") @Min(0) float surface,
            @RequestParam("price") @Min(0) float price,
            @RequestParam("description") @Size(max=2000) String description,
            @PathVariable("id") long id,
            @RequestHeader(value="Authorization",required=false) String jwt
            //@formatter: on
    ) throws OwnerMismatchException {
        //we first try to check get the owner's id from jwt owner (already validated)
        String userName=jwtService.extractUsername(jwt.substring(7));
        UserInfo user=userInfoService.getUserByUsername(userName);
        long ownerId=user.getId();
        RentalDto candidate = rentalMapper.toDto(service.getRentalById(id));
        if (ownerId==candidate.getOwner_id()) {
            //token's owner matches with rental's owner's id
            Rental modification=Rental.builder()
                .id(id)
                .name(name)
                .surface(surface)
                .price(price)
                .picture(candidate.getPicture())
                .description(description)
                .user(user)
                .created_at(candidate.getCreated_at())
                .build();
            return rentalMapper.toDto(service.saveRental(modification));
        } else {
            throw new OwnerMismatchException();
        }
    }
}
