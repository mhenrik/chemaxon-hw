package com.mhenrik.chemaxon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class FileProcessor {

    private String path;

    public FileProcessor(String path) {
        this.path = path;
    }

    public Map<String, Integer> process() throws IOException {

        Map<String, Integer> result = new HashMap<>();
        InputStreamReader in = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(path));
        String line;
        String splitter = " ";

        try (BufferedReader bufferedReader = new BufferedReader(in)) {

            while ((line = bufferedReader.readLine()) != null) {

                String[] preference = line.split(splitter);
                result = VoteCounter.vote(Arrays.asList(preference));

            }
        }

        return result;

    }

    public static List<String> findMostPopularChild(Map<String, Integer> result) {

        long max = result.values().stream()
                .max(Comparator.naturalOrder()).get();

        return result.entrySet().stream()
                .filter(vote -> vote.getValue() == max)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
