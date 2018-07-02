package com.mhenrik.chemaxon;

import java.io.IOException;
import java.util.List;

public class PopularChildFinder {

    public static void main(String[] args) throws IOException {

        String path = "Gizineni.txt";
        FileProcessor fileProcessor = new FileProcessor(path);


        List<String> mostPopularChildren = FileProcessor.findMostPopularChild(fileProcessor.process());

        mostPopularChildren.forEach(System.out::println);
    }
}
