package gherkinexecutor.Feature_Tic_Tac_Toe_Game
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Feature_Tic_Tac_Toe_Game{

    @Test
    fun test_Scenario_check_the_prints(){
        val feature_Tic_Tac_Toe_Game_glue_object = Feature_Tic_Tac_Toe_Game_glue()

        val stringListList1 = listOf<List<String>>(
           listOf<String>(
            "0",
            "x",
            "0",
            ),
           listOf<String>(
            "x",
            "0",
            "x",
            ),
           listOf<String>(
            "0",
            "x",
            "0",
            ),
            )
        feature_Tic_Tac_Toe_Game_glue_object.Given_board_is(stringListList1)

        val objectList2 = listOf<Move>(
            Move(
                row = "1",
                column = "2",
                mark = "X",
                ),
            )
        feature_Tic_Tac_Toe_Game_glue_object.When_move_is(objectList2)

        val table3 = 
            """
            | 0  | X  | 0  |
            | x  | 0  | x  |
            | 0  | x  | 0  |
            """.trimIndent()
        feature_Tic_Tac_Toe_Game_glue_object.Then_board_is_now(table3)
        }
    @Test
    fun test_Scenario_Make_a_move(){
        val feature_Tic_Tac_Toe_Game_glue_object = Feature_Tic_Tac_Toe_Game_glue()

        val stringListList1 = listOf<List<String>>(
           listOf<String>(
            "",
            "",
            "",
            ),
           listOf<String>(
            "",
            "",
            "",
            ),
           listOf<String>(
            "",
            "",
            "",
            ),
            )
        feature_Tic_Tac_Toe_Game_glue_object.Given_board_is(stringListList1)

        val objectList2 = listOf<Move>(
            Move(
                row = "1",
                column = "2",
                mark = "X",
                ),
            )
        feature_Tic_Tac_Toe_Game_glue_object.When_move_is(objectList2)

        val table3 = 
            """
            |   | X  |   |
            |   |    |   |
            |   |    |   |
            """.trimIndent()
        feature_Tic_Tac_Toe_Game_glue_object.Then_board_is_now(table3)
        }
    @Test
    fun test_Scenario_Make_a_move_using_single_element(){
        val feature_Tic_Tac_Toe_Game_glue_object = Feature_Tic_Tac_Toe_Game_glue()

        val stringListList1 = listOf<List<String>>(
           listOf<String>(
            "",
            "",
            "",
            ),
           listOf<String>(
            "",
            "",
            "",
            ),
           listOf<String>(
            "",
            "",
            "",
            ),
            )
        feature_Tic_Tac_Toe_Game_glue_object.Given_board_is(stringListList1)

        val stringListList2 = listOf<List<String>>(
           listOf<String>(
            "1,2,x",
            ),
            )
        feature_Tic_Tac_Toe_Game_glue_object.When_one_move_is(stringListList2)

        val table3 = 
            """
            |   | X  |   |
            |   |    |   |
            |   |    |   |
            """.trimIndent()
        feature_Tic_Tac_Toe_Game_glue_object.Then_board_is_now(table3)
        }
    @Test
    fun test_Scenario_Make_a_move0(){
        val feature_Tic_Tac_Toe_Game_glue_object = Feature_Tic_Tac_Toe_Game_glue()

        val stringListList1 = listOf<List<String>>(
           listOf<String>(
            "",
            "",
            "",
            ),
           listOf<String>(
            "",
            "",
            "",
            ),
           listOf<String>(
            "",
            "",
            "",
            ),
            )
        feature_Tic_Tac_Toe_Game_glue_object.Given_board_is(stringListList1)

        val objectList2 = listOf<Move>(
            Move(
                row = "1",
                column = "2",
                mark = "X",
                ),
            Move(
                row = "2",
                column = "3",
                mark = "O",
                ),
            )
        feature_Tic_Tac_Toe_Game_glue_object.When_moves_are(objectList2)

        val table3 = 
            """
            |   | X  |    |
            |   |    | O  |
            |   |    |    |
            """.trimIndent()
        feature_Tic_Tac_Toe_Game_glue_object.Then_board_is_now(table3)
        }
    }

