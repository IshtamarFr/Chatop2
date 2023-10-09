package fr.ishtamar.chatop.service;

import fr.ishtamar.chatop.repository.UserInfoRepository;
import fr.ishtamar.chatop.dto.UserDto;
import fr.ishtamar.chatop.entity.UserInfo;
import fr.ishtamar.chatop.exceptionhandler.EmailAlreadyUsedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    private UserInfoRepository repository;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //[X-01] Choose findByName or findByEmail
        Optional<UserInfo> userDetail = repository.findByEmail(username);

        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String addUser(UserInfo userInfo) throws EmailAlreadyUsedException {
        Optional<UserInfo> userDetail = repository.findByEmail(userInfo.getEmail());
        if (userDetail.isPresent()){
            throw new EmailAlreadyUsedException();
        } else {
            userInfo.setPassword(encoder.encode(userInfo.getPassword()));
            repository.save(userInfo);
            return "User Added Successfully";
        }
    }

    public UserDto getUserDtoByUsername(String username) throws UsernameNotFoundException {
        //[X-01] Choose findByName or findByEmail
        Optional<UserInfo> userDetail = repository.findByEmail(username);
        // Converting userDetail to UserDto
        return userDetail.map(UserDto::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }
}
