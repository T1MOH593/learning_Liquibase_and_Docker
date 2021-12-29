package by.vlad.liquibase_starter.document;

import lombok.Builder;
import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shortFiles")
@Data
@Builder
public class ShortFile {

    @Id
    private String title;

    private Binary binary;
}
