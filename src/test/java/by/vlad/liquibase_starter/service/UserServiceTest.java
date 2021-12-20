package by.vlad.liquibase_starter.service;

import by.vlad.liquibase_starter.dto.BelarusbankDto;
import by.vlad.liquibase_starter.entity.User;
import by.vlad.liquibase_starter.mapper.UserDtoMapper;
import by.vlad.liquibase_starter.repository.UserRepository;
import by.vlad.liquibase_starter.util.PropertiesUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({
        MockitoExtension.class
})
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RestTemplate restTemplate;
    @Spy
    private UserDtoMapper mapper;

    @InjectMocks
    private UserService userService;

    private final BelarusbankDto belarusbankDto1 = BelarusbankDto.builder()
            .price(BigDecimal.TEN)
            .build();
    private final BelarusbankDto belarusbankDto2 = BelarusbankDto.builder()
            .price(BigDecimal.ONE)
            .build();
    private final BelarusbankDto[] belarusbankDtos = new BelarusbankDto[]{belarusbankDto1, belarusbankDto2};

    @Test
    void findUserByIdTest() {
        doReturn(Optional.of(new User())).when(userRepository).findById(1L);

        var userDto = userService.findUserById(1L);
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void setPriceToUserDtoTest() {
        doReturn(new ResponseEntity<>(belarusbankDtos, HttpStatus.OK)).when(restTemplate)
                .getForEntity(PropertiesUtil.BELARUSBANK_URL, BelarusbankDto[].class);

        var price = userService.getMostProfitablePrice();
        assertThat(price).isEqualTo(belarusbankDto2.getPrice());
    }

    @Test
    void getMostProfitablePriceTest() {
        doReturn(new ResponseEntity<>(belarusbankDtos, HttpStatus.OK)).when(restTemplate)
                .getForEntity(PropertiesUtil.BELARUSBANK_URL, BelarusbankDto[].class);

        var price = userService.getMostProfitablePrice();
        assertThat(price).isEqualTo(belarusbankDto2.getPrice());
    }
}
