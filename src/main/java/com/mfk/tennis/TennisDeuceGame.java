package com.mfk.tennis;

// Class provides a deuce/advantage state of a game.
//
// Needs to cater for WinningGame at TennisDeuceGame level. Implemented
// but the Acceptance Tests do not currently require it.
//
// A suggested refactoring is to introduce a Score object that can be used to
// encapsulate the "Score" and player names. However, letting commonality of
// use emerge before doing so. Would remove duplicated methods like:
// 'scoreDifference' & 'winningPlayer'.
//
public class TennisDeuceGame implements TennisGameDelegate {

    private final TennisGame game;
    private final String player1;
    private final String player2;
    private final int[] score;

    TennisDeuceGame(TennisGame game, String player1, String player2) {
        this.game = game;
        this.player1 = player1;
        this.player2 = player2;
        this.score = new int[]{ 0, 0 };
    }

    //@Override
    public void pointWonBy(String player) {
        int playerIndex = playerIndexFor(player);
        score[playerIndex] += 1;
        if (isGameWon()) {
            String winningPlayer = winningPlayer();
            game.notifyGameOver(winningPlayer);
        }
    }

    //@Override
    public String score() {
        if (isDeuce()) {
            return "Deuce";
        }
        return String.format("Advantage %s", advantagedPlayer());
    }

    private boolean isGameWon() {
        return scoreDifference(2);
    }

    private boolean scoreDifference(int difference) {
        return Math.max(score[0], score[1]) - Math.min(score[0], score[1]) >= difference;
    }

    private String winningPlayer() {
        return score[0] > score[1] ? player1 : player2;
    }

    private String advantagedPlayer() {
        return score[0] > score[1] ? player1 : player2;
    }

    private boolean isDeuce() {
        return score[0] == score[1];
    }

    private int playerIndexFor(String player) {
        return player1.equals(player) ? 0 : 1;
    }
}
