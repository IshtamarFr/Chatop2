package fr.ishtamar.chatop.service;

import fr.ishtamar.chatop.repository.UserInfoRepository;
import fr.ishtamar.chatop.entity.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserInfoServiceTest {
    @Mock
    UserInfoRepository userInfoRepository;
    @Mock
    PasswordEncoder encoder;
    @InjectMocks
    UserInfoService userInfoService;

    UserInfo mockUser;
    @BeforeEach
        void init() {
            mockUser=new UserInfo();
            mockUser.setName("mockTest");
            mockUser.setPassword("123456");
            mockUser.setEmail("mock@testmock.com");
    }

    @Test
    public void testAddUser() {
        userInfoService.addUser(mockUser);
        verify(userInfoRepository,times(1)).save(mockUser);
    }
}
