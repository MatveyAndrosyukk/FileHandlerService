package com.example.filehandlerservice.data;

import com.example.filehandlerservice.model.Chapter;
import lombok.Data;

import java.util.ArrayList;

@Data
public class FileResponseData {
    private ArrayList<Chapter> chapters;

    public FileResponseData(ArrayList<Chapter> chapters) {
        this.chapters = chapters;
    }
}
