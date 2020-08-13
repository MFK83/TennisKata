package com.mfk.tennis;

import java.util.Arrays;
import java.util.List;

// Class provides a default state of a game, where scores go 0, 15, 30, 40.
//
// Could be separated out into rule, score and formatting but that could
// be seen as overkill at this stage.
//
// Needs to cater for WinningGame at TennisDefaultGame level. Implemented
// but the Acceptance Tests do not currently require it.
//
// A suggested refactoring is to introduce a Score object that can be used to
// encapsulate the "Score" and player names. However, letting commonality of
// use emerge before doing so. Would remove duplicated methods like:
// 'scoreDifference' & 'winningPlayer'.
//
// Note: the magic numbers should be replaced with meaningful constant names.
//
class TennisDefaultGame implements TennisGameDelegate {

    private final List<String> scoreRepresentations = Arrays.asList("0", "15", "30", "40", "e");

    private final TennisGame game;
    private final String player1;
    private final String player2;
    private final int[] score;

    TennisDefaultGame(TennisGame game, String player1, String player2) {
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
        } else if (isDeuce()) {
            game.notifyGameDeuce();
        }
    }

    //@Override
    public String score() {
        String player1Score = formatScore(player1Score());
        String player2Score = formatScore(player2Score());
        return String.format("%s-%s", player1Score, player2Score);
    }

    private boolean isGameWon() {
        return scoreDifference(2) && (score[0] == 4 || score[1] == 4);
    }

    private boolean scoreDifference(int difference) {
        return Math.max(score[0], score[1]) - Math.min(score[0], score[1]) >= difference;
    }

    private String winningPlayer() {
        return score[0] > score[1] ? player1 : player2;
    }

    private boolean isDeuce() {
        return (score[0] == 3 && score[1] == 3);
    }

    private String formatScore(int score) {
        return scoreRepresentations.get(score);
    }

    private int playerIndexFor(String player) {
        return player1.equals(player) ? 0 : 1;
    }

    private int player1Score() {
        return playerScore(player1);
    }

    private int player2Score() {
        return playerScore(player2);
    }

    private int playerScore(String player) {
        int playerIndex = playerIndexFor(player);
        return score[playerIndex];
    }
}
