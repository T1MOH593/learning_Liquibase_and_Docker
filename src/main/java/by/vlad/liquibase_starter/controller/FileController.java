package by.vlad.liquibase_starter.controller;

import by.vlad.liquibase_starter.dto.FileDto;
import by.vlad.liquibase_starter.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return fileService.saveFile(file);
    }

    @GetMapping("/download")
    public FileDto downloadFile(@RequestParam("filename") String id) throws IOException {
        return fileService.getFile(id);
    }
}
