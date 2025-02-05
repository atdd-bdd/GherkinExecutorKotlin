package gherkinexecutor.Feature_Examples
data class NameValue(
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
    fun toNameValue() : NameValue{
        return NameValue(
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
