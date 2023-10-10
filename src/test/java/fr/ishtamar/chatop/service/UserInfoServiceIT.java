package fr.ishtamar.chatop.service;

import fr.ishtamar.chatop.dto.UserDto;
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
        //[X-01] Choose test@test.com or Ishta
        UserDetails user= userInfoService.loadUserByUsername("test@test.com");
        assertThat(user.getUsername()).isEqualTo("test@test.com");
    }

    @Test
    public void testUserDto() {
        //[X-01] Choose test@test.com or Ishta
        UserDto userDto=userInfoService.getUserDtoByUsername("test@test.com");
        assertThat(userDto.getName()).isEqualTo("Ishta");
    }

    @Test void testGetUserById() {
        UserDetails user=userInfoService.getUserById(10L);
        assertThat(user.getUsername()).isEqualTo("test@test.com");
    }
}
