package com.example.filehandlerservice.cotroller;

import com.example.filehandlerservice.data.FileResponseData;
import com.example.filehandlerservice.model.Chapter;
import com.example.filehandlerservice.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping()
    public ResponseEntity<FileResponseData> getFile(@RequestParam("file") MultipartFile file) throws IOException {
        ArrayList<Chapter> chapters = new ArrayList<>();

        fileService.saveFile(file);
        chapters = fileService.getChapters(chapters, file);

        FileResponseData responseData = new FileResponseData(chapters);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
