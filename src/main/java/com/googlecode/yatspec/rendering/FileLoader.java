package com.googlecode.yatspec.rendering;

import com.googlecode.totallylazy.Strings;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileLoader {

    public FileLoader() {
    }

    public String loadFile(String fileName) {
        return Strings.toString(getClass().getClassLoader().getResourceAsStream(fileName));
    }

    //TODO used scanner before check the implications
    public String loadResourceAsStringWithDelimiter(String fileName) {
        Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream(fileName));
        String contents = scanner.useDelimiter("\\A").next();
        scanner.close();
        return contents;
    }

    //TODO this appears to strip off line breaks, thanks
    public String loadFileJava8Style(String fileName) {
        final Path path;
        Stream<String> lines = null;
        String contents = null;
        try {
            URI uri = getClass().getClassLoader().getResource(fileName).toURI();
            path = Paths.get(uri);
            lines = Files.lines(path);
            contents = lines.collect(Collectors.joining());
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        } finally {
            if (lines != null) {
                lines.close();
            }
        }
        return contents;
    }
}
