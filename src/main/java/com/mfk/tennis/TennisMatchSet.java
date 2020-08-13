package com.mfk.tennis;

// Class provides Tennis Match Set functionality.
//
// A suggested refactoring is to introduce a Score object that can be used to
// encapsulate the "Score" and player names. However, letting commonality of
// use emerge before doing so. Note I prefer composition over inheritance.
//
class TennisMatchSet {

    private final TennisGame game;
    private final int[] score;
    private final String player1;
    private final String player2;

    TennisMatchSet(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.score = new int[]{ 0, 0 };
        this.game = new TennisGame(this, player1, player2);
    }

    void pointWonBy(String player) {
        game.pointWonBy(player);
    }

    String score() {
        String gameScore = game.score();
        if (haveGameScore(gameScore)) {
            gameScore = ", " + gameScore;
        }
        return String.format("%s-%s%s", score[0], score[1], gameScore);
    }

    private boolean haveGameScore(String gameScore) {
        return !"".equals(gameScore);
    }

    public void notifyGameWon(String winningPlayer) {
        int playerIndex = player1.equals(winningPlayer) ? 0 : 1;
        score[playerIndex] += 1;
    }
}
