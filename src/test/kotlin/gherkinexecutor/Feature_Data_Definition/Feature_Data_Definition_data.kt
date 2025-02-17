package gherkinexecutor.Feature_Data_Definition
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
