package com.mfk.tennis;

// Class represents the finished state of a game, essentially a No Operation.
//
// Note: In a full implementation where TennisMatchSet had multiple games
// this would not be used !
//
public class TennisFinishedGame implements TennisGameDelegate {

    //@Override
    public void pointWonBy(String player) {
    }

    //@Override
    public String score() {
        return "";
    }
}
