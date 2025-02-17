package gherkinexecutor.Feature_Full_Test
data class MyClass(
    val one: String = "aaa",
    val two: String = "1",
)
{
    fun toInternalClass() : InternalClass{
        return InternalClass(
            one,
            two.toInt(),
        ) }
}
data class InternalClass(
    val one: String = "aaa",
    val two: Int = "1".toInt(),
) {
    fun toMyClass() : MyClass{
        return MyClass(
            one,
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
            aString,
            aDouble.toDouble(),
        ) }
}
data class ATestInternal(
    val anInt: Int = "0".toInt(),
    val aString: String = " ",
    val aDouble: Double = "1.2".toDouble(),
) {
    fun toATest() : ATest{
        return ATest(
            anInt.toString(),
            aString,
            aDouble.toString(),
        ) }
}
data class NameValue(
    val iD: String = "",
    val value: String = "0",
)
data class TemperatureCalculation(
    val f: String = "0",
    val c: String = "0",
    val notes: String = "",
)
{
    fun toTemperatureCalculationInternal() : TemperatureCalculationInternal{
        return TemperatureCalculationInternal(
            f.toInt(),
            c.toInt(),
            notes,
        ) }
}
data class TemperatureCalculationInternal(
    val f: Int = "0".toInt(),
    val c: Int = "0".toInt(),
    val notes: String = "",
) {
    fun toTemperatureCalculation() : TemperatureCalculation{
        return TemperatureCalculation(
            f.toString(),
            c.toString(),
            notes,
        ) }
}
data class DomainTermID(
    val value: String = "0",
    val valid: String = "false",
    val notes: String = "",
)
{
    fun toDomainTermIDInternal() : DomainTermIDInternal{
        return DomainTermIDInternal(
            value,
            valid.toBoolean(),
            notes,
        ) }
}
data class DomainTermIDInternal(
    val value: String = "0",
    val valid: Boolean = "false".toBoolean(),
    val notes: String = "",
) {
    fun toDomainTermID() : DomainTermID{
        return DomainTermID(
            value,
            valid.toString(),
            notes,
        ) }
}
data class LabelValue(
    val label: String = "",
    val value: String = "0",
)
{
    fun toLabelValueInternal() : LabelValueInternal{
        return LabelValueInternal(
            label,
            value.toInt(),
        ) }
}
data class LabelValueInternal(
    val label: String = "",
    val value: Int = "0".toInt(),
) {
    fun toLabelValue() : LabelValue{
        return LabelValue(
            label,
            value.toString(),
        ) }
}
data class ExampleClass(
    val fieldA: String = "y",
    val fieldB: String = "x",
)
data class ExampleClassWithBlanks(
    val field_1: String = "y",
    val field_2: String = "x",
)
