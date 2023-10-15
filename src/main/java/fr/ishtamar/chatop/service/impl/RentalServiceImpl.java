package fr.ishtamar.chatop.service.impl;

import fr.ishtamar.chatop.dto.RentalDto;
import fr.ishtamar.chatop.entity.Rental;
import fr.ishtamar.chatop.exceptionhandler.EntityNotFoundException;
import fr.ishtamar.chatop.mapper.RentalMapper;
import fr.ishtamar.chatop.repository.RentalRepository;
import fr.ishtamar.chatop.service.RentalService;
import fr.ishtamar.chatop.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService {
    @Autowired
    private RentalRepository repository;
    @Autowired
    private RentalMapper rentalMapper;

    @Override
    public List<RentalDto> getAllRentals() {
        return repository.findAll().stream().map(rental -> rentalMapper.toDto(rental)).toList();
    }

    @Override
    public RentalDto getRentalById(final Long id) throws EntityNotFoundException {
        Optional<Rental>rental=repository.findById(id);
        if (rental.isPresent()) {
            return rentalMapper.toDto(rental.get());
        } else {
            throw new EntityNotFoundException(Rental.class,"id",id.toString());
        }
    }

    @Override
    public String savePicture(MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String filecode = FileUploadUtil.saveFile(fileName, multipartFile);
        return ("/Files-Upload/" + filecode);
    }

    @Override
    public RentalDto saveRental(Rental rental) {
        return rentalMapper.toDto(repository.save(rental));
    }
}
