package com.mhenrik.chemaxon;

import com.mhenrik.chemaxon.exception.EmptyFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class FileProcessor {

    private String path;
    private VoteCounter voteCounter;

    public FileProcessor(String path, VoteCounter voteCounter) {
        checkNotNullOrEmpty(path);
        this.path = path;
        this.voteCounter = voteCounter;
    }

    public Map<String, Integer> process () throws IOException, EmptyFileException {

        Map<String, Integer> result = new HashMap<>();

        for (List<String> line : readFile()) {
            result = voteCounter.vote(line);
        }

        return result;
    }

    private List<List<String>> readFile() throws IOException, EmptyFileException {

        List<List<String>> lines = new ArrayList<>();

        InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
        InputStreamReader in;

        if (is != null) {
            in = new InputStreamReader(is);
        } else {
            throw new IllegalArgumentException("Incorrect file path!");
        }

        String line;
        String splitter = " ";

        try (BufferedReader bufferedReader = new BufferedReader(in)) {

            if (bufferedReader.readLine() == null) {
                throw new EmptyFileException();
            }

            while ((line = bufferedReader.readLine()) != null) {

                String[] preference = line.split(splitter);
                lines.add(Arrays.asList(preference));
            }
        }
        return lines;
    }

    private void checkNotNullOrEmpty(String toCheck) {
        if (toCheck == null || toCheck.isEmpty()) {
            throw new IllegalArgumentException(toCheck + " can't be empty!");
        }
    }
}
