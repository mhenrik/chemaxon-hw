package com.mhenrik.chemaxon;

import com.mhenrik.chemaxon.exception.EmptyFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class FileProcessor {

    private String path;

    public FileProcessor(String path) {
        checkNotNullOrEmpty(path);
        this.path = path;
    }

    public Map<String, Integer> process() throws IOException, EmptyFileException {

        Map<String, Integer> result = new HashMap<>();

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
                result = VoteCounter.vote(Arrays.asList(preference));
            }
        }

        return result;
    }

    private void checkNotNullOrEmpty(String toCheck) {
        if (toCheck == null || toCheck.isEmpty()) {
            throw new IllegalArgumentException(toCheck + " can't be empty!");
        }
    }
}
