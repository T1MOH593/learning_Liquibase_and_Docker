package by.vlad.liquibase_starter.service;

import by.vlad.liquibase_starter.dto.BelarusbankDto;
import by.vlad.liquibase_starter.dto.UserDto;
import by.vlad.liquibase_starter.exception_handling.NoSuchEntityException;
import by.vlad.liquibase_starter.mapper.UserDtoMapper;
import by.vlad.liquibase_starter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;

import static java.util.Comparator.comparing;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final UserDtoMapper mapper;

    @Value( "${belarusbank.api.url}" )
    private String belarusbankApiUrl;


    public UserService(UserRepository userRepository, RestTemplate restTemplate, UserDtoMapper mapper) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.mapper = mapper;
    }

    public UserDto findUserById(Long id) {
        var maybeUser = userRepository.findById(id);

        if (maybeUser.isEmpty()) {
            throw new NoSuchEntityException("There is no user with id " + id);
        } else {
            return mapper.EntityToDto(maybeUser.get());
        }
    }

    public BigDecimal setPriceToUserDto(UserDto userDto) {
        BelarusbankDto[] belarusbankDtos = restTemplate.getForEntity(belarusbankApiUrl,
                BelarusbankDto[].class).getBody();

        if (belarusbankDtos != null) {
            userDto.setPrice(belarusbankDtos[1].getPrice());
        }
        return userDto.getPrice();
    }

    public BigDecimal getMostProfitablePrice() {
        var belarusbankDtos = restTemplate.getForEntity(belarusbankApiUrl,
                BelarusbankDto[].class).getBody();
        if (belarusbankDtos == null) {
            return BigDecimal.ZERO;
        } else {
            return Arrays.stream(belarusbankDtos).min(comparing(BelarusbankDto::getPrice)).get().getPrice();
        }
    }
}
