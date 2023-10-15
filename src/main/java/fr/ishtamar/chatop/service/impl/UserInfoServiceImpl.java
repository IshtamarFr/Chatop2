package fr.ishtamar.chatop.service.impl;

import fr.ishtamar.chatop.exceptionhandler.EntityNotFoundException;
import fr.ishtamar.chatop.repository.UserInfoRepository;
import fr.ishtamar.chatop.dto.UserDto;
import fr.ishtamar.chatop.entity.UserInfo;
import fr.ishtamar.chatop.exceptionhandler.EmailAlreadyUsedException;
import fr.ishtamar.chatop.service.UserInfoDetails;
import fr.ishtamar.chatop.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoRepository repository;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws EntityNotFoundException {
        Optional<UserInfo> userDetail = repository.findByEmail(username);

        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new EntityNotFoundException(UserDetails.class,"username",username));
    }

    @Override
    public void addUser(UserInfo userInfo) throws EmailAlreadyUsedException {
        Optional<UserInfo> userDetail = repository.findByEmail(userInfo.getEmail());
        if (userDetail.isPresent()){
            throw new EmailAlreadyUsedException();
        } else {
            userInfo.setPassword(encoder.encode(userInfo.getPassword()));
            repository.save(userInfo);
        }
    }

    @Override
    public UserInfo getUserById(Long id) throws EntityNotFoundException {
        Optional<UserInfo> user = repository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new EntityNotFoundException(UserInfo.class,"id",id.toString());
        }
    }

    @Override
    public UserInfo getUserByUsername(String username) throws EntityNotFoundException {
        Optional<UserInfo> user = repository.findByEmail(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new EntityNotFoundException(UserInfo.class,"username",username);
        }
    }
}
