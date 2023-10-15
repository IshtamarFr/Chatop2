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

@Service
public class RentalServiceImpl implements RentalService {
    @Autowired
    private RentalRepository repository;

    @Override
    public List<Rental> getAllRentals() {
        return repository.findAll();
    }

    @Override
    public Rental getRentalById(final Long id) throws EntityNotFoundException {
        return repository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(Rental.class,"id",id.toString()));
    }

    @Override
    public String savePicture(MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String filecode = FileUploadUtil.saveFile(fileName, multipartFile);
        return ("/Files-Upload/" + filecode);
    }

    @Override
    public Rental saveRental(Rental rental) {
        return repository.save(rental);
    }
}
