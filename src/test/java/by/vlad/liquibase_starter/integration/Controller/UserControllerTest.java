package by.vlad.liquibase_starter.integration.Controller;

import by.vlad.liquibase_starter.dto.BelarusbankDto;
import by.vlad.liquibase_starter.util.PropertiesUtil;
import liquibase.integration.spring.SpringLiquibase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;
import java.math.BigDecimal;

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

    private final BelarusbankDto belarusbankDto1 = BelarusbankDto.builder()
            .price(BigDecimal.ONE)
            .build();
    private final BelarusbankDto belarusbankDto2 = BelarusbankDto.builder()
            .price(BigDecimal.TEN)
            .build();
    private final BelarusbankDto[] belarusbankDtos = new BelarusbankDto[]{belarusbankDto1, belarusbankDto2};

//    @Container
//    public static PostgreSQLContainer<?> postgresql = new PostgreSQLContainer<>("postgres:13");

//    @Configuration
//    @EnableJpaRepositories
//    @EntityScan
//    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
//        @Override
//        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
//            TestPropertyValues.of(
//                    "spring.datasource.url=" + postgresql.getJdbcUrl(),
//                    "spring.datasource.username=" + postgresql.getUsername(),
//                    "spring.datasource.password=" + postgresql.getPassword(),
//                    "spring.datasource.driver-class-name=" + postgresql.getDriverClassName())
//                    .applyTo(configurableApplicationContext.getEnvironment());
//        }
//
//        @Bean
//        public SpringLiquibase springLiquibase(DataSource dataSource) {
//            SpringLiquibase liquibase = new SpringLiquibase();
//            liquibase.setDropFirst(true);
//            liquibase.setDataSource(dataSource);
//            liquibase.setChangeLog("classpath:/db/changelog/db.changelog-master.yml");
//            return liquibase;
//        }
//    }

    @Test
    void getUserWithPriceTest() throws Exception {
        doReturn(new ResponseEntity<>(belarusbankDtos, HttpStatus.OK)).when(restTemplate)
                .getForEntity(PropertiesUtil.BELARUSBANK_URL, BelarusbankDto[].class);

        mvc.perform(get("/users/1"))
                .andExpect(content().string(BigDecimal.TEN.toString()));
    }

    @Test
    void getMostProfitableDepartmentTest() throws Exception {
        doReturn(new ResponseEntity<>(belarusbankDtos, HttpStatus.OK)).when(restTemplate)
                .getForEntity(PropertiesUtil.BELARUSBANK_URL, BelarusbankDto[].class);

        mvc.perform(get("/mostProfitableDepartment"))
            .andExpect(content().string(BigDecimal.ONE.toString()));
    }
}
