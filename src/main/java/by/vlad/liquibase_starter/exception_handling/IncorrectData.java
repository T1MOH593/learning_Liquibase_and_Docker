package by.vlad.liquibase_starter.exception_handling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncorrectData {

    private String errorMessage;
}
