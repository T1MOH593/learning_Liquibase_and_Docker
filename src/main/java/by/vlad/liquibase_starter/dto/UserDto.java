package by.vlad.liquibase_starter.dto;

import by.vlad.liquibase_starter.entity.Company;
import by.vlad.liquibase_starter.entity.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;

    private String username;

    private String name;

    private BigDecimal price;

    private Role role;

    @JsonBackReference
    private Company company;
}
