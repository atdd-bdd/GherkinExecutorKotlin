package gherkinexecutor.Feature_Simple_Test
data class Test(
    var anInt: String = "0",
    var aString: String = " ",
    var aDouble: String = "1.2",
)
data class TestInternal(
    var anInt: Int= "0".toInt(),
    var aString: String= " ".toString(),
    var aDouble: Double= "1.2".toDouble(),
)
