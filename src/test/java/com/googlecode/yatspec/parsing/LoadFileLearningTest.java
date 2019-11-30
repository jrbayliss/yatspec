package com.googlecode.yatspec.parsing;

import com.googlecode.yatspec.rendering.FileLoader;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class LoadFileLearningTest {

    private FileLoader fileLoader = new FileLoader();
    private String MULTI_LINE_FILENAME = "loadfile/multi-line.txt";
    private String MULTI_LINE_CONTENTS = "line 1\n" + "line 2\n";

    @Test
    public void loadFile_java8() {
        String contents = fileLoader.loadFileJava8Style(MULTI_LINE_FILENAME);
        assertThat(contents, Matchers.is("line 1line 2"));
    }

    @Test
    public void loadFile_resourceAsString() {
        String contents = fileLoader.loadFile(MULTI_LINE_FILENAME);
        assertThat(contents, Matchers.is(MULTI_LINE_CONTENTS));
    }

    @Test
    public void loadFile_resourceAsStringWithDelimiter() {
        String contents =  fileLoader.loadResourceAsStringWithDelimiter(MULTI_LINE_FILENAME);
        assertThat(contents, Matchers.is(MULTI_LINE_CONTENTS));
    }
}
