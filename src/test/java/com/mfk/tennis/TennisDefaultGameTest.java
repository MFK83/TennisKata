package com.mfk.tennis;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

// Class under test is TennisDefaultGame.
// NOTE: This unit test only covers the cases where
// default game notifies the game that Game state should change.
//
// Not all unit tests are done because of time/urgency to
// provide code.
//
// This is how all classes would be unit tested, ie: mocking
// interactions with other Classes.
//
public class TennisDefaultGameTest {

    private static String playerOne = "player 1";
    private static String playerTwo = "player 2";

    private TennisGame gameProxy;
    private TennisDefaultGame defaultGame;

    @Before
    public void setup() {
        gameProxy = mock(TennisGame.class);
        defaultGame = new TennisDefaultGame(gameProxy, playerOne, playerTwo);
    }

    @Test
    public void shouldNotifyGameProxyWhenGameWon() {
        defaultGame.pointWonBy(playerOne); // 15
        defaultGame.pointWonBy(playerOne); // 30
        defaultGame.pointWonBy(playerOne); // 40
        defaultGame.pointWonBy(playerOne); // Win
        verify(gameProxy).notifyGameOver(playerOne);
    }

    @Test
    public void shouldNotifyGameProxyWhenGameInDeuce() {
        defaultGame.pointWonBy(playerOne); // 15-0
        defaultGame.pointWonBy(playerTwo); // 15-15
        defaultGame.pointWonBy(playerOne); // 30-15
        defaultGame.pointWonBy(playerTwo); // 30-30
        defaultGame.pointWonBy(playerOne); // 40-30
        defaultGame.pointWonBy(playerTwo); // 40-40
        verify(gameProxy).notifyGameDeuce();
    }
}