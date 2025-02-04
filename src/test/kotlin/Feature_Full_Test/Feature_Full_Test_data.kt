package gherkinexecutor.Feature_Full_Test
data class MyClass(
    var one: String = "aaa",
    var two: String = "1",
)
data class MyClassInternal(
    var one: String= "aaa".toString(),
    var two: Int= "1".toInt(),
)
data class ATest(
    var anInt: String = "0",
    var aString: String = " ",
    var aDouble: String = "1.2",
)
data class ATestInternal(
    var anInt: Int= "0".toInt(),
    var aString: String= " ".toString(),
    var aDouble: Double= "1.2".toDouble(),
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
    var f: Int= "0".toInt(),
    var c: Int= "0".toInt(),
    var notes: String= "".toString(),
)
