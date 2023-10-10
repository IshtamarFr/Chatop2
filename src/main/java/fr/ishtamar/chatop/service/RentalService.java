package fr.ishtamar.chatop.service;

import fr.ishtamar.chatop.dto.RentalDto;
import fr.ishtamar.chatop.entity.Rental;
import fr.ishtamar.chatop.exceptionhandler.EntityNotFoundException;
import fr.ishtamar.chatop.repository.RentalRepository;
import fr.ishtamar.chatop.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {
    @Autowired
    private RentalRepository repository;

    /**
     * Gets all rentals
     * @return List of all rentals
     */
    public List<RentalDto> getAllRentals() {
        List<Rental>rentals=repository.findAll();
        List<RentalDto>rentalDtos=new ArrayList<>();
        for (Rental rental : rentals) {
            RentalDto eDto = new RentalDto(rental);
            eDto.setUser_id(rental.getUser().getId());
            rentalDtos.add(eDto);
        }
        return rentalDtos;
    }

    /**
     * Gets a rental by Id if it exists
     * @param id - id for rental
     * @return Rental
     * @throws EntityNotFoundException
     */
    public Rental getRentalById(final Long id) throws EntityNotFoundException {
        Optional<Rental>candidate=repository.findById(id);
        if (candidate.isPresent()) {
            return candidate.get();
        } else {
            throw new EntityNotFoundException(Rental.class,"id",id.toString());
        }
    }

    /**
     * Saves picture to local folder and returns file path and name
     * @param multipartFile - Picture to save
     * @return path/"obfuscated file name"
     * @throws IOException
     */
    public String savePicture(MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String filecode = FileUploadUtil.saveFile(fileName, multipartFile);
        return ("/Files-Upload/" + filecode);
    }

    public Rental saveRental(Rental rental) {
        return repository.save(rental);
    }
}
