package com.mhenrik.chemaxon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VoteCounterTest {

    private VoteCounter voteCounter;

    @BeforeEach
    public void setup(){
        voteCounter = new VoteCounter();
    }

    @Test
    public void testVoteCounter(){

        List<String> voteList = new ArrayList<String>(){{
            add("Anna");
            add("Bela");
            add("Cecil");
            add("Denes");
            add("Bela");
            add("Anna");
        }};

        HashMap<String, Integer> expected = new HashMap<String, Integer>() {{
            put("Bela", 2);
            put("Cecil", 1);
            put("Denes", 1);
        }};

        HashMap<String, Integer> actual = voteCounter.vote(voteList);

        assertEquals(expected, actual);
    }

    @Test
    public void testVoteCounterIfVoteListIsNullThenThrowIllegalArgumentException(){

        assertThrows(IllegalArgumentException.class, () -> voteCounter.vote(null));
    }

    @Test
    public void testVoteCounterIfVoteListIsEmptyThenThrowIllegalArgumentException(){

        assertThrows(IllegalArgumentException.class, () -> voteCounter.vote(Collections.emptyList()));
    }

    @Test
    public void testVoteCounterIfVoterOnlyVotesForHerSelf(){

        List<String> voteList = new ArrayList<String>(){{
            add("Anna");
            add("Anna");
        }};


        HashMap<String, Integer> actual = voteCounter.vote(voteList);

        assertEquals(Collections.emptyMap(), actual);
    }

}