package com.mfk.tennis;

// Defines the contract for each type of TennisGame state.
// Implementors are concrete TennisGame states, eg: TennisDeuceGame.
//
public interface TennisGameDelegate {

    void pointWonBy(String player);
    String score();
}
