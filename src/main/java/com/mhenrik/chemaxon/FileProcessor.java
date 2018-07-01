package com.mhenrik.chemaxon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FileProcessor {

    public Map<String, Integer> read(String path) throws IOException {

        Map<String, Integer> result = new HashMap<>();
        InputStreamReader in = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(path));
        String line = "";
        String splitter = " ";

        try (BufferedReader bufferedReader = new BufferedReader(in)) {

            while ((line = bufferedReader.readLine()) != null) {

                String[] preference = line.split(splitter);
                result = VoteCounter.vote(Arrays.asList(preference));

            }
        }

        result.forEach(
                (key, value) -> System.out.println(key + ": " + value ));

        return result;

    }

    public static String findMostPopularChild(Map<String, Integer> result) {

        return result.entrySet().stream()
                .max((vote1, vote2) -> vote1.getValue() > vote2.getValue() ? 1 : -1).get().getKey();
    }

    public static void main(String[] args) throws IOException {

        FileProcessor fileProcessor = new FileProcessor();
        System.out.println(findMostPopularChild(fileProcessor.read("Gizineni.txt")));

    }
}
