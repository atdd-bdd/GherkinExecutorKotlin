package gherkinexecutor.Feature_Full_Test
data class MyClass(
    val one: String = "aaa",
    val two: String = "1",
)
{
    fun toInternalClass() : InternalClass{
        return InternalClass(
            one.toString(),
            two.toInt(),
        ) }
}
data class InternalClass(
    val one: String= "aaa".toString(),
    val two: Int= "1".toInt(),
) {
    fun toMyClass() : MyClass{
        return MyClass(
            one.toString(),
            two.toString(),
        ) }
}
data class ATest(
    val anInt: String = "0",
    val aString: String = " ",
    val aDouble: String = "1.2",
)
{
    fun toATestInternal() : ATestInternal{
        return ATestInternal(
            anInt.toInt(),
            aString.toString(),
            aDouble.toDouble(),
        ) }
}
data class ATestInternal(
    val anInt: Int= "0".toInt(),
    val aString: String= " ".toString(),
    val aDouble: Double= "1.2".toDouble(),
) {
    fun toATest() : ATest{
        return ATest(
            anInt.toString(),
            aString.toString(),
            aDouble.toString(),
        ) }
}
data class NameValue(
    val iD: String = "",
    val value: String = "0",
)
data class NameValue0(
    val iD: String = "",
    val value: String = "0",
)
{
    fun toNameValueInternal() : NameValueInternal{
        return NameValueInternal(
            iD.toString(),
            value.toInt(),
        ) }
}
data class NameValueInternal(
    val iD: String= "".toString(),
    val value: Int= "0".toInt(),
) {
    fun toNameValue0() : NameValue0{
        return NameValue0(
            iD.toString(),
            value.toString(),
        ) }
}
data class TemperatureComparison(
    val f: String = "0",
    val c: String = "0",
    val notes: String = "",
)
{
    fun toTemperatureComparisonInternal() : TemperatureComparisonInternal{
        return TemperatureComparisonInternal(
            f.toInt(),
            c.toInt(),
            notes.toString(),
        ) }
}
data class TemperatureComparisonInternal(
    val f: Int= "0".toInt(),
    val c: Int= "0".toInt(),
    val notes: String= "".toString(),
) {
    fun toTemperatureComparison() : TemperatureComparison{
        return TemperatureComparison(
            f.toString(),
            c.toString(),
            notes.toString(),
        ) }
}
