package fr.ishtamar.chatop.service;

import fr.ishtamar.chatop.dto.UserDto;
import fr.ishtamar.chatop.entity.UserInfo;
import fr.ishtamar.chatop.service.impl.UserInfoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserInfoServiceImplIT {
    @Autowired
    private UserInfoServiceImpl userInfoServiceImpl;

    @Test
    public void testLoadUserByUserName() {
        UserDetails user= userInfoServiceImpl.loadUserByUsername("test@test.com");
        assertThat(user.getUsername()).isEqualTo("test@test.com");
    }

    @Test void testGetUserById() {
        UserInfo user= userInfoServiceImpl.getUserById(10L);
        assertThat(user.getEmail()).isEqualTo("test@test.com");
    }
}
