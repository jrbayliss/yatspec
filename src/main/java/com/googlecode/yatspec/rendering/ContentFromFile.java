package com.googlecode.yatspec.rendering;

public class ContentFromFile implements Content {

    private final String file;

    public ContentFromFile(String file) {
        this.file = file;
    }

    @Override
    public String toString() {
        FileLoader fileLoader = new FileLoader();
        String contents = fileLoader.loadFile(file);
        return contents;
    }
}
