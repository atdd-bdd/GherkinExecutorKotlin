package gherkinexecutor.Feature_Robots
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Feature_Robots{

    @Test
    fun test_Scenario_Movement_of_Two_Bots_Not_Colliding(){
        val feature_Robots_glue_object = Feature_Robots_glue()

        val stringListList1 = listOf<List<String>>(
           listOf<String>(
            "R, Bot2",
            "",
            "",
            "",
            "",
            ),
           listOf<String>(
            "",
            "",
            "",
            "",
            "",
            ),
           listOf<String>(
            "",
            "",
            "R, Bot1, South",
            "R, Bot3, West",
            "",
            ),
           listOf<String>(
            "",
            "",
            "",
            "B",
            "",
            ),
           listOf<String>(
            "",
            "",
            "B",
            "",
            "",
            ),
            )
        feature_Robots_glue_object.Given_map_is(stringListList1)

        val objectList2 = listOf<IDAndAction>(
            IDAndAction(
                iD = "Bot1",
                action = "Move",
                ),
            IDAndAction(
                iD = "Bot3",
                action = "Move",
                ),
            )
        feature_Robots_glue_object.When_actions_are(objectList2)

        val stringListList3 = listOf<List<String>>(
           listOf<String>(
            "R, Bot2",
            "",
            "",
            "",
            "",
            ),
           listOf<String>(
            "",
            "",
            "",
            "",
            "",
            ),
           listOf<String>(
            "",
            "",
            "R, Bot3, West",
            "",
            "",
            ),
           listOf<String>(
            "",
            "",
            "R, Bot1, South",
            "B",
            "",
            ),
           listOf<String>(
            "",
            "",
            "B",
            "",
            "",
            ),
            )
        feature_Robots_glue_object.Then_map_result_is(stringListList3)
        }
    @Test
    fun test_Scenario_Movement_of_Two_Bots_Colliding(){
        val feature_Robots_glue_object = Feature_Robots_glue()

        val stringListList1 = listOf<List<String>>(
           listOf<String>(
            "R, Bot2",
            "",
            "",
            "",
            "",
            ),
           listOf<String>(
            "",
            "",
            "",
            "",
            "",
            ),
           listOf<String>(
            "",
            "",
            "R, Bot1, South",
            "R, Bot3, West",
            "",
            ),
           listOf<String>(
            "",
            "",
            "",
            "B",
            "",
            ),
           listOf<String>(
            "",
            "",
            "B",
            "",
            "",
            ),
            )
        feature_Robots_glue_object.Given_map_is(stringListList1)

        val objectList2 = listOf<IDAndAction>(
            IDAndAction(
                iD = "Bot3",
                action = "Move",
                ),
            IDAndAction(
                iD = "Bot1",
                action = "Move",
                ),
            )
        feature_Robots_glue_object.When_actions_are(objectList2)

        val stringListList3 = listOf<List<String>>(
           listOf<String>(
            "R, Bot2",
            "",
            "",
            "",
            "",
            ),
           listOf<String>(
            "",
            "",
            "",
            "",
            "",
            ),
           listOf<String>(
            "",
            "",
            "",
            "R, Bot3, West",
            "",
            ),
           listOf<String>(
            "",
            "",
            "R, Bot1, South",
            "B",
            "",
            ),
           listOf<String>(
            "",
            "",
            "B",
            "",
            "",
            ),
            )
        feature_Robots_glue_object.Then_map_result_is(stringListList3)
        }
    @Test
    fun test_Scenario_Vision_Reported(){
        val feature_Robots_glue_object = Feature_Robots_glue()

        val stringListList1 = listOf<List<String>>(
           listOf<String>(
            "R, Bot2",
            "",
            "",
            "",
            "",
            ),
           listOf<String>(
            "",
            "",
            "",
            "",
            "",
            ),
           listOf<String>(
            "",
            "",
            "R, Bot1",
            "R, Bot3",
            "",
            ),
           listOf<String>(
            "",
            "",
            "",
            "B",
            "",
            ),
           listOf<String>(
            "",
            "",
            "B",
            "",
            "",
            ),
            )
        feature_Robots_glue_object.Given_map_is(stringListList1)

        val stringListList2 = listOf<List<String>>(
           listOf<String>(
            "Bot2",
            ),
            )
        feature_Robots_glue_object.When_vision_is_computed_for(stringListList2)

        val stringListList3 = listOf<List<String>>(
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
        feature_Robots_glue_object.Then_vision_is(stringListList3)

        val stringListList4 = listOf<List<String>>(
           listOf<String>(
            "Bot1",
            ),
            )
        feature_Robots_glue_object.When_vision_is_computed_for(stringListList4)

        val stringListList5 = listOf<List<String>>(
           listOf<String>(
            "",
            "",
            "",
            ),
           listOf<String>(
            "",
            "",
            "R",
            ),
           listOf<String>(
            "",
            "",
            "B",
            ),
            )
        feature_Robots_glue_object.Then_vision_is(stringListList5)
        }
    @Test
    fun test_Scenario_Allowed_Actions_other_than_Turn(){
        val feature_Robots_glue_object = Feature_Robots_glue()

        val stringListList1 = listOf<List<String>>(
           listOf<String>(
            "W",
            "W",
            "W",
            ),
           listOf<String>(
            "B",
            "X",
            "R",
            ),
           listOf<String>(
            "",
            "",
            "B",
            ),
            )
        feature_Robots_glue_object.Given_vision_for_a_bot_is(stringListList1)

        val objectList2 = listOf<AllowableActions>(
            AllowableActions(
                direction = "North",
                holding = "Any",
                action = "Any",
                allowed = "No",
                ),
            AllowableActions(
                direction = "West",
                holding = "Any",
                action = "Step",
                allowed = "No",
                ),
            AllowableActions(
                direction = "West",
                holding = "No",
                action = "Take",
                allowed = "Yes",
                ),
            AllowableActions(
                direction = "West",
                holding = "Any",
                action = "Drop",
                allowed = "No",
                ),
            AllowableActions(
                direction = "South",
                holding = "Any",
                action = "Step",
                allowed = "Yes",
                ),
            AllowableActions(
                direction = "South",
                holding = "Yes",
                action = "Drop",
                allowed = "Yes",
                ),
            AllowableActions(
                direction = "South",
                holding = "Any",
                action = "Take",
                allowed = "No",
                ),
            AllowableActions(
                direction = "East",
                holding = "Any",
                action = "Any",
                allowed = "No",
                ),
            AllowableActions(
                direction = "",
                holding = "",
                action = "",
                allowed = "",
                ),
            )
        feature_Robots_glue_object.Star_Allowed_actions_are(objectList2)
        }
    @Test
    fun test_Scenario_Aroma_Domain_Term(){
        val feature_Robots_glue_object = Feature_Robots_glue()

        val stringListList1 = listOf<List<String>>(
           listOf<String>(
            "1",
            ),
           listOf<String>(
            "2",
            ),
            )
        feature_Robots_glue_object.Star_Aromas_are(stringListList1)
        }
    @Test
    fun test_Scenario_Scent(){
        val feature_Robots_glue_object = Feature_Robots_glue()

        val stringListList1 = listOf<List<String>>(
           listOf<String>(
            "ERROR IN TABLE LINE |",
            ),
            )
        feature_Robots_glue_object.Given_scent_area_is(stringListList1)

        feature_Robots_glue_object.When_scent_is_computed()

        val stringListList3 = listOf<List<String>>(
           listOf<String>(
            "40",
            ),
            )
        feature_Robots_glue_object.Then_scent_has_value(stringListList3)
        }
    @Test
    fun test_Scenario_Convert_Vision_to_String(){
        val feature_Robots_glue_object = Feature_Robots_glue()

        val stringListList1 = listOf<List<String>>(
           listOf<String>(
            "W",
            "W",
            "W",
            ),
           listOf<String>(
            "B",
            "",
            "R",
            ),
           listOf<String>(
            "",
            "",
            "B",
            ),
            )
        feature_Robots_glue_object.Given_vision_for_a_bot_is(stringListList1)

        feature_Robots_glue_object.When_converted_to_string()

        val string3 = 
            """
            W,W,W,B, ,R, , ,B,
            """.trimIndent()
        feature_Robots_glue_object.Then_result_is(string3)
        }
    }

