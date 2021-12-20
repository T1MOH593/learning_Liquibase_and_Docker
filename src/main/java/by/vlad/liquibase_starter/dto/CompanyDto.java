package by.vlad.liquibase_starter.dto;

import by.vlad.liquibase_starter.entity.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDto {

    private Integer id;

    private String name;

    @JsonManagedReference
    private List<User> users = new ArrayList<>();
}
