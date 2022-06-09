package com.example.filehandlerservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Chapter {
    private String name;
    private List<String> strings = new ArrayList<>();
    private Chapter subChapter;

    public Chapter(String chapterName) {
        this.name = chapterName;
    }

    public void addStringToStrings(String string){
        strings.add(string);
    }
}
