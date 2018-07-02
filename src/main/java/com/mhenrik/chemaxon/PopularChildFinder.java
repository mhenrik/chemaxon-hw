package com.mhenrik.chemaxon;

import com.mhenrik.chemaxon.exception.EmptyFileException;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PopularChildFinder {

    public static void main(String[] args) throws IOException, EmptyFileException {

        String path = "Gizineni.txt";
        FileProcessor fileProcessor = new FileProcessor(path);

        Map<String, Integer> result = fileProcessor.process();

        List<String> mostPopularChildren = findMostPopularChild(result);

        mostPopularChildren.forEach(System.out::println);
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
