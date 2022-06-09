package com.example.filehandlerservice.service;

import com.example.filehandlerservice.model.Chapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static com.example.filehandlerservice.utils.ChapterUtils.countLattice;
import static com.example.filehandlerservice.utils.ChapterUtils.findCurrentChapter;

@Service
public class FileService {
    @Value("${uploadDir}")
    private String uploadDir;

    public void saveFile(MultipartFile file) throws IOException {
        Path dir = Paths.get(uploadDir);
        Path filepath = Paths.get(dir.toString(), file.getOriginalFilename());
        file.transferTo(filepath);
    }

    public ArrayList<Chapter> getChapters(ArrayList<Chapter> chapters, MultipartFile file) throws IOException {
        try (FileInputStream inputStream = new FileInputStream("uploads/" + file.getOriginalFilename())) {
            try (BufferedReader buf = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = buf.readLine()) != null) {
                    int count = 0;
                    if (line.startsWith("#")) {
                        count = countLattice(line, count);
                        if (count == 1) {
                            chapters.add(new Chapter(line));
                        } else {
                            Chapter currentChapter = findCurrentChapter(chapters);
                            currentChapter.setSubChapter(new Chapter(line));
                        }
                    } else {
                        Chapter currentChapter = findCurrentChapter(chapters);
                        currentChapter.addStringToStrings(line);
                    }
                }
            }
        }

        return chapters;
    }
}
