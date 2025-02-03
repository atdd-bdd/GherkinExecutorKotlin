package gherkinexecutor.Feature_Tic_Tac_Toe_Game
data class Move(
    var row: String = "0",
    var column: String = "0",
    var mark: String = "",
) {
        constructor(moveString: String) : this() {
        val parts = moveString.split(",")
        if (parts.size == 3) {
            this.row = parts[0]
            this.column = parts[1]
            this.mark = parts[2]
        } else {
            throw IllegalArgumentException("Invalid move string format")
        }
    }
    fun convert(): MoveInternal {
        return MoveInternal(this.row.toInt(), this.column.toInt(), this.mark[0])
    }
}
data class MoveInternal(
    var row: Int= 0,
    var column: Int= 0 ,
    var mark: Char= ' ',
)

