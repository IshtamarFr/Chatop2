package fr.ishtamar.chatop.service;

import fr.ishtamar.chatop.exceptionhandler.EntityNotFoundException;
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

    /**
     * Tries to find user corresponding to unique username
     * @param username Unique name or email
     * @return User corresponding to username
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws EntityNotFoundException {
        //[X-01] Choose findByName or findByEmail
        Optional<UserInfo> userDetail = repository.findByEmail(username);

        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new EntityNotFoundException(UserDetails.class,"username",username));
    }

    /**
     * Tries to add user if they don't exist
     * @param userInfo User's data
     * @return a validation String
     * @throws EmailAlreadyUsedException
     */
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

    /**
     * Tries to find user corresponding to unique unsername
     * @param username Unique name or email
     * @return User DTO (data-protection safe)
     * @throws UsernameNotFoundException
     */
    public UserDto getUserDtoByUsername(String username) throws EntityNotFoundException {
        //[X-01] Choose findByName or findByEmail
        Optional<UserInfo> userDetail = repository.findByEmail(username);
        // Converting userDetail to UserDto
        return userDetail.map(UserDto::new)
                .orElseThrow(() -> new EntityNotFoundException(UserDetails.class,"username",username));
    }

    /**
     * Tries to find UserDto by long id
     * @param id Long id for user
     * @return UserDto corresponding
     * @throws EntityNotFoundException
     */
    public UserDto getUserDtoById(Long id) throws EntityNotFoundException {
        Optional<UserInfo> userDetail = repository.findById(id);
        // Converting userDetail to UserDto
        return userDetail.map(UserDto::new)
                .orElseThrow(() -> new EntityNotFoundException(UserInfo.class,"id",id.toString()));
    }

    /**
     * Tries to find User by long id
     * @param id Long id for user
     * @return UserInfo corresponding
     * @throws EntityNotFoundException
     */
    public UserInfo getUserById(Long id) throws EntityNotFoundException {
        Optional<UserInfo> user = repository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new EntityNotFoundException(UserInfo.class,"id",id.toString());
        }
    }

    /**
     * Tries to find user by its username
     * @param username String for user
     * @return UserInfo corresponding
     * @throws EntityNotFoundException
     */
    public UserInfo getUserByUsername(String username) throws EntityNotFoundException {
        Optional<UserInfo> user = repository.findByEmail(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new EntityNotFoundException(UserInfo.class,"username",username);
        }
    }
}
