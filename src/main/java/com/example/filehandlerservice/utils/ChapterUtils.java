package com.example.filehandlerservice.utils;

import com.example.filehandlerservice.model.Chapter;

import java.util.ArrayList;

public class ChapterUtils {
    public static Chapter findCurrentChapter(ArrayList<Chapter> chapters) {
        Chapter currentChapter = null;
        Chapter nextChapter = chapters.get(chapters.size() - 1).getSubChapter();
        if (nextChapter == null) {
            currentChapter = chapters.get(chapters.size() - 1);
        } else {
            while (nextChapter != null) {
                currentChapter = nextChapter;
                nextChapter = nextChapter.getSubChapter();
            }
        }

        return currentChapter;
    }

    public static int countLattice(String line, int count) {
        for (char element : line.toCharArray()) {
            if (element == '#') count++;
        }
        return count;
    }
}
