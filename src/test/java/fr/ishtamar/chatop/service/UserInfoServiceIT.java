package fr.ishtamar.chatop.service;

import fr.ishtamar.chatop.dto.UserDto;
import fr.ishtamar.chatop.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserInfoServiceIT {
    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void testLoadUserByUserName() {
        UserDetails user= userInfoService.loadUserByUsername("test@test.com");
        assertThat(user.getUsername()).isEqualTo("test@test.com");
    }

    @Test
    public void testUserDto() {
        UserDto userDto=userInfoService.getUserDtoByUsername("test@test.com");
        assertThat(userDto.getName()).isEqualTo("Ishta");
    }

    @Test void testGetUserById() {
        UserInfo user=userInfoService.getUserById(10L);
        assertThat(user.getEmail()).isEqualTo("test@test.com");
    }
}
