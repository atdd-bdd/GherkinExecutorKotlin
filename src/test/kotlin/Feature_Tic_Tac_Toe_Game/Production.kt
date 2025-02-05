package gherkinexecutor.Feature_Tic_Tac_Toe_Game

class TicTacToeGame {
    var gameBoard = mutableListOf(mutableListOf<String>())
    fun setBoard(value: List<List<String>>) {
        gameBoard.removeAt(0)
        for (row in value) {
            val inRow = mutableListOf<String>()
            for (cell in row) {
                inRow.add(cell)
            }
            gameBoard.add(inRow)
        }
        println("Game Board "+ gameBoard)
        println("End Game Board")
    }

    override fun toString(): String {
        return ListOfListToString(this.gameBoard)
    }

    fun makeAMove(row: Int, column: Int, mark: Char) {
        println("Row " + row + " Col " + column + "Mark" + mark)
        gameBoard[row - 1][column - 1] = mark.toString()
        println("Game Board " + gameBoard)
        println("End Game Board")
    }
}

fun ListOfListToString(value: List<List<String>>): String {
    var maxSize = 0;
    var maxSizes = mutableListOf<Int>()
    for (row in value) {
        var column= 0
        for (cell in row) {
            if (maxSizes.size < column + 1 )
                maxSizes.add(0)
            if (cell.length > maxSizes[column])
                maxSizes[column] = cell.length
            column++
        }
    }

    var result = ""
    for (row in value) {
        result += "|"
        var column = 0
        for (cell in row) {
            result += " "
            val number_spaces = maxSizes[column] - cell.length + 1
            result += cell
            for (i in 0..number_spaces) {
                result += " "
            }
            result += "|"
            column++
        }
        result += "\n"
    }
    result = result.trimEnd('\n')
    return result
}
