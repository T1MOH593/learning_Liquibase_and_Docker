package by.vlad.liquibase_starter.service;

import by.vlad.liquibase_starter.document.ShortFile;
import by.vlad.liquibase_starter.dto.FileDto;
import by.vlad.liquibase_starter.exception_handling.NoSuchDocumentException;
import by.vlad.liquibase_starter.repository.ShortFileRepository;
import lombok.RequiredArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService {

    @Autowired
    private ShortFileRepository shortFileRepository;
    @Autowired
    private GridFsTemplate gridFsTemplate;

    public String saveFile(MultipartFile file) throws IOException {
        //                 < 16MB
        if (file.getSize() < 1024 * 1024 * 16) {
            return saveShortFile(file);
        } else {
            return saveLongFile(file);
        }
    }

    private String saveLongFile(MultipartFile file) throws IOException {
        var objectId = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType());
        return objectId.toHexString();
    }

    public FileDto getFile(String id) throws IOException {
        var maybeShortFile = shortFileRepository.findById(id);
        if (maybeShortFile.isPresent()) {
            var shortFile = maybeShortFile.get();
            return FileDto.builder()
                    .inputStream(new ByteArrayInputStream(shortFile.getBinary().getData()))
                    .title(shortFile.getTitle())
                    .build();
        } else {
            var file = gridFsTemplate.findOne(Query.query(Criteria.where("filename").is(id)));
            if (file == null) {
                throw new NoSuchDocumentException("There is no file with id = " + id);
            } else {
                var fsResource = gridFsTemplate.getResource(file);
                return FileDto.builder()
                        .title(file.getFilename())
                        .inputStream(fsResource.getInputStream())
                        .build();
            }
        }
    }

    private String saveShortFile(MultipartFile file) throws IOException {
        ShortFile shortFile = ShortFile.builder()
                .title(file.getOriginalFilename())
                .binary(new Binary(BsonBinarySubType.BINARY, file.getBytes()))
                .build();
        return shortFileRepository.insert(shortFile).getTitle();
    }
}
