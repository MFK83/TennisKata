package com.mfk.tennis;

// Class provides public interface to Domain model.
//
public class TennisMatch {

    private final TennisMatchSet matchSet;

    public TennisMatch(String player1, String player2) {
        matchSet = new TennisMatchSet(player1, player2);
    }

    public void pointWonBy(String player) {
        matchSet.pointWonBy(player);
    }

    public String score() {
        return matchSet.score();
    }
}
