package com.mfk.tennis;

// Class provides a proxy to a TennisGameDelegate instance that
// handles the rules for a particular state of the Game.
//
// TennisGameDelegates call back on this proxy to notify it of
// game state changes which in turn change the TennisGameDelegate.
// For example, see notifyGameDeuce()
//
public class TennisGame {

    private final TennisMatchSet matchSet;
    private final String player1;
    private final String player2;

    private TennisGameDelegate delegate;

    TennisGame(TennisMatchSet matchSet, String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.matchSet = matchSet;
        delegate = new TennisDefaultGame(this, player1, player2);
    }

    public void pointWonBy(String player) {
        delegate.pointWonBy(player);
    }

    public String score() {
        return delegate.score();
    }

    public void notifyGameOver(String winningPlayer) {
        matchSet.notifyGameWon(winningPlayer);
        delegate = new TennisFinishedGame();
    }

    public void notifyGameDeuce() {
        delegate = new TennisDeuceGame(this, player1, player2);
    }
}
