package gherkinexecutor.Feature_Full_Test
data class MyClass(
    var one: String = "aaa",
    var two: String = "1",
)
data class MyClassInternal(
    var one: String= "aaa",
    var two: Int= 1,
)
data class NameValue(
    var iD: String = "",
    var value: String = "0",
)
data class Temperature(
    var f: String = "0",
    var c: String = "0",
    var notes: String = "",
)
data class TemperatureInternal(
    var f: Int= 0,
    var c: Int= 0,
    var notes: String="" ,
) 
