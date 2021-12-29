package by.vlad.liquibase_starter.dto;

import lombok.*;
import org.bson.types.Binary;

import java.io.InputStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDto {
    String title;
    InputStream inputStream;
}
