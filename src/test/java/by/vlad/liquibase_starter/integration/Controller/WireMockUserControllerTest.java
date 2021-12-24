package by.vlad.liquibase_starter.integration.Controller;

import by.vlad.liquibase_starter.util.PropertiesUtil;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureWireMock(
        stubs = "classpath*:**/[group]/[artifact]/**/mappings/**/*.json",
        httpsPort = 443,
        port = 8081)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=validate"
})
@ExtendWith(SpringExtension.class)
//@Testcontainers
public class WireMockUserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WireMockServer wireMockServer;

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
        wireMockServer.stubFor(get(PropertiesUtil.BELARUSBANK_URL)
                .willReturn(aResponse()));

        mvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andExpect(content().string(BigDecimal.TEN.toString()));
    }

    @Test
    void getMostProfitableDepartmentTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/mostProfitableDepartment"))


                .andExpect(content().string(BigDecimal.ONE.toString()));

    }
}
