package by.vlad.liquibase_starter.service;

import by.vlad.liquibase_starter.dto.UserDto;
import by.vlad.liquibase_starter.entity.User;
import by.vlad.liquibase_starter.mapper.UserDtoMapper;
import by.vlad.liquibase_starter.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.mockito.verification.VerificationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith({
        MockitoExtension.class
})
@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RestTemplate restTemplate;
    @Spy
    private UserDtoMapper mapper;

    @InjectMocks
    private UserService userService;

    @Test
    void findUserByIdTest() {
//        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        doReturn(Optional.of(new User())).when(userRepository).findById(1L);
        var userDto = userService.findUserById(1L);
        verify(userRepository, times(1)).findById(1L);

    }
}
