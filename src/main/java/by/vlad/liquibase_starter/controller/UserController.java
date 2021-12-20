package by.vlad.liquibase_starter.controller;

import by.vlad.liquibase_starter.dto.BelarusbankDto;
import by.vlad.liquibase_starter.dto.UserDto;
import by.vlad.liquibase_starter.entity.User;
import by.vlad.liquibase_starter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("users/{id}")
    private UserDto getUserWithPrice(@PathVariable(name = "id") Long id) {
        UserDto userDto = userService.findUserById(id);
        return userService.setPriceToUserDto(userDto);
    }

    @GetMapping("/mostProfitableDepartment")
    private BelarusbankDto getMostProfitableDepartment() {
        return userService.getMostProfitableDepartment();
    }
}
