package com.example.tictactoe

import org.junit.Test

import org.junit.Assert.*

/**
 * unit test for TicTacToe, which will execute on the development machine (host).
 */
class TicTacToeGameTest {
//    @Test
//    fun example() {
//        assertEquals(4, 2 + 2)
//    }
    @Test
    fun boardIsReset() {
        val game = TicTacToeGame()
        for (row in 0 until TicTacToeGame.NUM_ROWS) {
            for (col in 0 until TicTacToeGame.NUM_COLUMNS) {
                assertEquals("", game.stringForButtonAt(row, col))
            }
        }
        assertEquals(TicTacToeGame.GameState.X_TURN, game.gameState)
    }

    @Test
    fun pressRegisters() {
        val game = TicTacToeGame()
        game.pressButtonAt(1, 2)
        assertEquals("X", game.stringForButtonAt(1, 2))
        assertEquals(TicTacToeGame.GameState.O_TURN, game.gameState)
        game.pressButtonAt(2, 2)
        assertEquals("O", game.stringForButtonAt(2, 2))
        assertEquals(TicTacToeGame.GameState.X_TURN, game.gameState)
        game.pressButtonAt(2, 2)
        assertEquals("O", game.stringForButtonAt(2, 2))
    }

    @Test
    fun pressOutOfBoundsIgnored() {
        val game = TicTacToeGame()
        assertEquals(TicTacToeGame.GameState.X_TURN, game.gameState)
        game.pressButtonAt(-1, 0)
        assertEquals(TicTacToeGame.GameState.X_TURN, game.gameState)
        game.pressButtonAt(0, -1)
        game.pressButtonAt(3, 2)
        game.pressButtonAt(0, 3)
        assertEquals(TicTacToeGame.GameState.X_TURN, game.gameState)
    }

    @Test
    fun detectWinEasy() {
        val game = TicTacToeGame()
        assertEquals(TicTacToeGame.GameState.X_TURN, game.gameState)
        game.pressButtonAt(0, 0) // X
        assertEquals(TicTacToeGame.GameState.O_TURN, game.gameState)
        game.pressButtonAt(2, 0) // O
        assertEquals(TicTacToeGame.GameState.X_TURN, game.gameState)
        game.pressButtonAt(0, 1) // X
        assertEquals(TicTacToeGame.GameState.O_TURN, game.gameState)
        game.pressButtonAt(2, 2) // O
        assertEquals(TicTacToeGame.GameState.X_TURN, game.gameState)
        game.pressButtonAt(0, 2) // X
        assertEquals(TicTacToeGame.GameState.X_WINS, game.gameState)
    }

    @Test
    fun detectWinForce() {
        val game = TicTacToeGame()
        assertEquals(TicTacToeGame.GameState.X_TURN, game.gameState)
        game.pressButtonAt(1, 1)
        assertEquals(TicTacToeGame.GameState.O_TURN, game.gameState)
        game.pressButtonAt(0, 1)
        assertEquals(TicTacToeGame.GameState.X_TURN, game.gameState)
        game.pressButtonAt(0, 0)
        assertEquals(TicTacToeGame.GameState.O_TURN, game.gameState)
        game.pressButtonAt(2, 2)
        assertEquals(TicTacToeGame.GameState.X_TURN, game.gameState)
        game.pressButtonAt(2, 0)
        assertEquals(TicTacToeGame.GameState.O_TURN, game.gameState)
        game.pressButtonAt(0, 2)
        assertEquals(TicTacToeGame.GameState.X_TURN, game.gameState)
        game.pressButtonAt(1, 0)
        assertEquals(TicTacToeGame.GameState.X_WINS, game.gameState)
    }

    @Test
    fun detectTie() {
        val game = TicTacToeGame()
        assertEquals(TicTacToeGame.GameState.X_TURN, game.gameState)
        game.pressButtonAt(1, 1)
        assertEquals(TicTacToeGame.GameState.O_TURN, game.gameState)
        game.pressButtonAt(0, 1)
        assertEquals(TicTacToeGame.GameState.X_TURN, game.gameState)
        game.pressButtonAt(0, 0)
        assertEquals(TicTacToeGame.GameState.O_TURN, game.gameState)
        game.pressButtonAt(2, 2)
        assertEquals(TicTacToeGame.GameState.X_TURN, game.gameState)
        game.pressButtonAt(2, 0)
        assertEquals(TicTacToeGame.GameState.O_TURN, game.gameState)
        game.pressButtonAt(0, 2)
        assertEquals(TicTacToeGame.GameState.X_TURN, game.gameState)
        game.pressButtonAt(1, 2)
        assertEquals(TicTacToeGame.GameState.O_TURN, game.gameState)
        game.pressButtonAt(1, 0)
        assertEquals(TicTacToeGame.GameState.X_TURN, game.gameState)
        game.pressButtonAt(2, 1)
        assertEquals(TicTacToeGame.GameState.TIE_GAME, game.gameState)
    }

    @Test
    fun detectXWinsBottomLeftToUpperRightDiagonal() {
        val game = TicTacToeGame()
        assertEquals(TicTacToeGame.GameState.X_TURN, game.gameState)
        game.pressButtonAt(0, 2)
        assertEquals(TicTacToeGame.GameState.O_TURN, game.gameState)
        game.pressButtonAt(0, 1)
        assertEquals(TicTacToeGame.GameState.X_TURN, game.gameState)
        game.pressButtonAt(1, 1)
        assertEquals(TicTacToeGame.GameState.O_TURN, game.gameState)
        game.pressButtonAt(2, 2)
        assertEquals(TicTacToeGame.GameState.X_TURN, game.gameState)
        game.pressButtonAt(2, 0)
        assertEquals(TicTacToeGame.GameState.X_WINS, game.gameState)
    }

}
