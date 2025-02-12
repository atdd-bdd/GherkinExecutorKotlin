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
    fun toMoveInternal() : MoveInternal {
        return MoveInternal(
            row.toInt(),
            column.toInt(),
            mark[0],
        ) }

}

data class MoveInternal(
    var row: Int= "0".toInt(),
    var column: Int= "0".toInt(),
    var mark: Char= " "[0],
) {
//    fun toMove() : Move{
//        return Move(
//            row.toString(),
//            column.toString(),
//            mark.toString(),
//        ) }
}


