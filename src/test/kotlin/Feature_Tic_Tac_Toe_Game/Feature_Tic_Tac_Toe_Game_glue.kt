package Feature_Tic_Tac_Toe_Game

import kotlin.test.assertEquals

import gherkinexecutor.Feature_Tic_Tac_Toe_Game.TicTacToeGame
class Feature_Tic_Tac_Toe_Game_glue {

    val ticTacToeGame = TicTacToeGame()
    fun Given_board_is(value: List<List<String>>) {
        ticTacToeGame.setBoard(value)
        println("**** Given board is ")
        println( ticTacToeGame)
    }

    fun When_move_is( value : List<Move>) {
        val oneMove = value[0]
        println(oneMove)
        val internalMove = oneMove.toMoveInternal()
        ticTacToeGame.makeAMove(internalMove.row, internalMove.column,
            internalMove.mark)
    }

    fun Then_board_is_now( value : String) {
       val current = ticTacToeGame.toString()
        assertEquals(value, current)
    }

    fun When_one_move_is( value : List<List<Move>>) {
        val oneMove = value[0][0]
        val internalMove = oneMove.toMoveInternal()
        ticTacToeGame.makeAMove(internalMove.row, internalMove.column,
            internalMove.mark)
    }

    fun When_moves_are( value : List<Move>) {
        for (oneMove in value) {
            val internalMove = oneMove.toMoveInternal()
            ticTacToeGame.makeAMove(internalMove.row, internalMove.column,
                internalMove.mark)
        }
    }
}
