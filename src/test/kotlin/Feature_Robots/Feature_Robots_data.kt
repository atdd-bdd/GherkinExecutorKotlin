package gherkinexecutor.Feature_Robots
data class IDAndAction(
    var iD: String = "0",
    var action: String = "",
) 
data class IDAndActionInternal(
    var iD: Int= 0,
    var action: String="" ,
) 
data class AllowableActions(
    var direction: String = "North",
    var holding: String = "Any",
    var action: String = "Any",
    var allowed: String = "No",
) 
