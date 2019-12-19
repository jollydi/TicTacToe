package com.example.tictactoe

import android.content.Context

class TicTacToeGame {

    private var board: Array<Array<Mark>> = Array(NUM_ROWS) { Array(NUM_COLUMNS) {Mark.MARK_NONE} }
    var gameState: GameState = GameState.X_TURN
    private var context: Context

    companion object {
        val NUM_ROWS = 3
        val NUM_COLUMNS = 3
    }

    enum class Mark {
        MARK_NONE,
        MARK_X,
        MARK_O
    }

    enum class GameState {
        X_TURN,
        O_TURN,
        X_WINS,
        O_WINS,
        TIE_GAME
    }

    constructor(context: Context) {
        this.context = context
    }

    init {
        resetGame()
    }

    fun resetGame() {
        board = Array(NUM_ROWS) { Array(NUM_COLUMNS) {Mark.MARK_NONE} }
        gameState = GameState.X_TURN
    }

    fun stringForButtonAt(row: Int, column: Int): String {
        if (row in 0 until NUM_ROWS && column in 0 until NUM_COLUMNS) {
            if (board[row][column] == Mark.MARK_X) {
                return "X"
            }
            if (board[row][column] == Mark.MARK_O) {
                return "O"
            }
        }
        return ""
    }

    fun pressButtonAt(row: Int, column: Int) {
        if (row !in 0 until NUM_ROWS || column !in 0 until NUM_COLUMNS) {
            return
        }

        if (board[row][column] != Mark.MARK_NONE) {
            return
        }

        if (gameState == GameState.X_TURN) {
            board[row][column] = Mark.MARK_X
            gameState = GameState.O_TURN
            checkForWin()
        } else if (gameState == GameState.O_TURN) {
            board[row][column] = Mark.MARK_O
            gameState = GameState.X_TURN
            checkForWin()
        }
    }

    private fun checkForWin() {
        if (gameState != GameState.X_TURN && gameState != GameState.O_TURN) {
            return
        }

        if (didPieceWin(Mark.MARK_X)) {
            gameState = GameState.X_WINS
        } else if (didPieceWin(Mark.MARK_O)) {
            gameState = GameState.O_WINS
        } else if (isBoardFull()) {
            gameState = GameState.TIE_GAME
        }

    }

    private fun didPieceWin(mark: Mark): Boolean {
        // Check for win in each row
        for (row in 0 until NUM_ROWS) {
            var winHere = true
            for (col in 0 until NUM_COLUMNS) {
                if (board[row][col] != mark) {
                    winHere = false
                }
            }
            if(winHere) {
                return true
            }
        }

        //Check for win in each column
        for (col in 0 until NUM_COLUMNS) {
            var winHere = true
            for (row in 0 until NUM_ROWS) {
                if (board[row][col] != mark) {
                    winHere = false
                }
            }
            if (winHere) {
                return true
            }
        }

        //Check for win in main diagonal
        var winHere = true
        for (row in 0 until NUM_ROWS) {
            if (board[row][row] != mark) {
                winHere = false
            }
        }
        if (winHere) {
            return true
        }

        //Check for win in off diagonal
        winHere = true
        for (row in 0 until NUM_ROWS) {
            if (board[row][NUM_ROWS - row - 1] != mark) {
                winHere = false
            }
        }
        if (winHere) {
            return true
        }

        return false
    }

    private fun isBoardFull(): Boolean {
        for (row in 0 until NUM_ROWS) {
            for (col in 0 until NUM_COLUMNS) {
                if (board[row][col] == Mark.MARK_NONE) {
                    return false
                }
            }
        }
        return true
    }

    fun stringForGameState(): String {
        return when (gameState) {
            GameState.X_TURN -> context.getString(R.string.x_turn)
            GameState.O_TURN -> context.getString(R.string.o_turn)
            GameState.X_WINS -> context.getString(R.string.x_wins)
            GameState.O_WINS -> context.getString(R.string.o_wins)
            GameState.TIE_GAME -> context.getString(R.string.tie_game)
        }
    }


}