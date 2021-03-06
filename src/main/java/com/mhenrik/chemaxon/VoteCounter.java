package com.mhenrik.chemaxon;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class VoteCounter {

    private HashMap<String, List<String>> votes = new HashMap<>();
    private HashMap<String, Integer> result = new HashMap<>();

    public HashMap<String, Integer> vote(List<String> voteList) {

        if (voteList == null || voteList.isEmpty()) {
            throw new IllegalArgumentException("Vote list cannot be empty!");
        }
        String voter = voteList.get(0);

        List<String> votesByVoter = IntStream.range(1, voteList.size())
                .filter(i -> !voteList.get(i).equals(voter))
                .mapToObj(voteList::get)
                .collect(Collectors.toList());

        votes.put(voter, votesByVoter);

        votes.get(voter).forEach(
                vote -> result.merge(vote, 1, Integer::sum));

        return result;

    }
}
