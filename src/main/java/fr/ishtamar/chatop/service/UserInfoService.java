package fr.ishtamar.chatop.service;

import fr.ishtamar.chatop.dto.UserDto;
import fr.ishtamar.chatop.entity.UserInfo;
import fr.ishtamar.chatop.exceptionhandler.EmailAlreadyUsedException;
import fr.ishtamar.chatop.exceptionhandler.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserInfoService extends UserDetailsService {
    /**
     * Tries to find user corresponding to unique username
     * @param username Unique name or email
     * @return User corresponding to username
     * @throws UsernameNotFoundException Username not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws EntityNotFoundException;

    /**
     * Tries to add user if they don't exist
     * @param userInfo User's data
     * @throws EmailAlreadyUsedException User is already registered
     */
    public void addUser(UserInfo userInfo) throws EmailAlreadyUsedException;

    /**
     * Tries to find user corresponding to unique unsername
     * @param username Unique name or email
     * @return User DTO (data-protection safe)
     * @throws UsernameNotFoundException Username not found
     */
    public UserDto getUserDtoByUsername(String username) throws EntityNotFoundException;

    /**
     * Tries to find UserDto by long id
     * @param id Long id for user
     * @return UserDto corresponding
     * @throws EntityNotFoundException User Id not found
     */
    public UserDto getUserDtoById(Long id) throws EntityNotFoundException;

    /**
     * Tries to find User by long id
     * @param id Long id for user
     * @return UserInfo corresponding
     * @throws EntityNotFoundException User Id not found
     */
    public UserInfo getUserById(Long id) throws EntityNotFoundException;

    /**
     * Tries to find user by its username
     * @param username String for user
     * @return UserInfo corresponding
     * @throws EntityNotFoundException Username not found
     */
    public UserInfo getUserByUsername(String username) throws EntityNotFoundException;
}
