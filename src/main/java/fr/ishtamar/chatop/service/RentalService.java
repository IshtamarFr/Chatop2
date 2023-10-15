package fr.ishtamar.chatop.service;

import fr.ishtamar.chatop.dto.RentalDto;
import fr.ishtamar.chatop.entity.Rental;
import fr.ishtamar.chatop.exceptionhandler.EntityNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RentalService {
    /**
     * Gets all rentals
     * @return List of all rentals
     */
    List<RentalDto> getAllRentals();

    /**
     * Gets a rental by Id if it exists
     * @param id id for rental
     * @return Rental
     * @throws EntityNotFoundException Rental id not found
     */
    RentalDto getRentalById(final Long id) throws EntityNotFoundException;

    /**
     * Saves picture to local folder and returns file path and name
     * @param multipartFile Picture to save
     * @return path/"obfuscated file name"
     * @throws IOException Couldn't save file in folder
     */
    String savePicture(MultipartFile multipartFile) throws IOException;

    /**
     * Saves rental into database
     * @param rental Rental to be saved
     * @return saved RentalDto
     */
    RentalDto saveRental(Rental rental);
}
