package gherkinexecutor.Feature_Full_Test
data class MyClass(
    var one: String = "aaa",
    var two: String = "1",
) {
    fun toInternalClass() : InternalClass{
        return InternalClass(
            one.toString(),
            two.toInt(),
        ) }
}
data class InternalClass(
    var one: String= "aaa".toString(),
    var two: Int= "1".toInt(),
) {
    fun toMyClass() : MyClass{
        return MyClass(
            one.toString(),
            two.toString(),
        ) }
}
data class ATest(
    var anInt: String = "0",
    var aString: String = " ",
    var aDouble: String = "1.2",
) {
    fun toATestInternal() : ATestInternal{
        return ATestInternal(
            anInt.toInt(),
            aString.toString(),
            aDouble.toDouble(),
        ) }
}
data class ATestInternal(
    var anInt: Int= "0".toInt(),
    var aString: String= " ".toString(),
    var aDouble: Double= "1.2".toDouble(),
) {
    fun toATest() : ATest{
        return ATest(
            anInt.toString(),
            aString.toString(),
            aDouble.toString(),
        ) }
}
data class NameValue(
    var iD: String = "",
    var value: String = "0",
)

data class Temperature(
    var f: String = "0",
    var c: String = "0",
    var notes: String = "",
) {
    fun toTemperatureInternal() : TemperatureInternal{
        return TemperatureInternal(
            f.toInt(),
            c.toInt(),
            notes.toString(),
        ) }
}
data class TemperatureInternal(
    var f: Int= "0".toInt(),
    var c: Int= "0".toInt(),
    var notes: String= "".toString(),
) {
    fun toTemperature() : Temperature{
        return Temperature(
            f.toString(),
            c.toString(),
            notes.toString(),
        ) }
}
