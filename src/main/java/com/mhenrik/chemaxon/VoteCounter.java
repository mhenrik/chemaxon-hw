package com.mhenrik.chemaxon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VoteCounter {

    private static HashMap<String, List<String>> votes = new HashMap<>();
    private static HashMap<String, Integer> result = new HashMap<>();

    public static HashMap<String, Integer> vote(List<String> voteList) {

        String voter = voteList.get(0);
        List<String> votesByVoter = new ArrayList<>();

        for (int i = 1; i < voteList.size() ; i++) {

            votesByVoter.add(voteList.get(i));
        }

        votes.put(voter, votesByVoter);

        for (String vote : votes.get(voter)) {

            result.merge(vote, 1, Integer::sum);

        }
        return result;

    }
}
