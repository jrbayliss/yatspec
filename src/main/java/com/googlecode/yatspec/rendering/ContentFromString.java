package com.googlecode.yatspec.rendering;

public class ContentFromString implements Content {

    private final String contents;

    public ContentFromString(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return contents;
    }
}
