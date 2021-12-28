package by.vlad.liquibase_starter.integration.Controller;

import by.vlad.liquibase_starter.configuration.ServiceConfiguration;
import by.vlad.liquibase_starter.dto.BelarusbankDto;
import by.vlad.liquibase_starter.util.TestPropertiesUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static by.vlad.liquibase_starter.util.TestPropertiesUtil.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Testcontainers
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=validate"
})
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private ServiceConfiguration serviceConfiguration;

    private final BelarusbankDto belarusbankDto1 = BelarusbankDto.builder()
            .price(BigDecimal.ONE)
            .build();
    private final BelarusbankDto belarusbankDto2 = BelarusbankDto.builder()
            .price(BigDecimal.TEN)
            .build();
    private final BelarusbankDto[] belarusbankDtos = new BelarusbankDto[]{belarusbankDto1, belarusbankDto2};

//    @Container
//    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13");
//
//    @DynamicPropertySource
//    static void postgresqlProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
//        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
//        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
//    }

    @Test
    void getUserWithPriceTest() throws Exception {
        doReturn(new ResponseEntity<>(belarusbankDtos, HttpStatus.OK)).when(restTemplate)
                .getForEntity(serviceConfiguration.getUrl(), BelarusbankDto[].class);

        mvc.perform(get(TestPropertiesUtil.USERS_1_URL))
                .andExpect(content().string(BigDecimal.TEN.toString()));
    }

    @Test
    void getMostProfitableDepartmentTest() throws Exception {
        doReturn(new ResponseEntity<>(belarusbankDtos, HttpStatus.OK)).when(restTemplate)
                .getForEntity(serviceConfiguration.getUrl(), BelarusbankDto[].class);

        mvc.perform(get(MOST_PROFITABLE_DEPARTMENT_URL))
            .andExpect(content().string(BigDecimal.ONE.toString()));
    }
}
