package by.vlad.liquibase_starter.repository;

import by.vlad.liquibase_starter.document.ShortFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortFileRepository extends MongoRepository<ShortFile, String> {
}
