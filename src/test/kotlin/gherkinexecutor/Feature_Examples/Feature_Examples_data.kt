package gherkinexecutor.Feature_Examples
data class LabelValue(
    val label: String = "",
    val value: String = "0",
)
{
    fun toLabelValueInternal() : LabelValueInternal{
        return LabelValueInternal(
            label.toString(),
            value.toInt(),
        ) }
}
data class LabelValueInternal(
    val label: String= "".toString(),
    val value: Int= "0".toInt(),
) {
    fun toLabelValue() : LabelValue{
        return LabelValue(
            label.toString(),
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
data class DomainTermID(
    val value: String = "0",
    val valid: String = "no",
    val notes: String = "",
)
{
    fun toDomainTermIDInternal() : DomainTermIDInternal{
        return DomainTermIDInternal(
            value.toString(),
            valid.toBoolean(),
            notes.toString(),
        ) }
}
data class DomainTermIDInternal(
    val value: String= "0".toString(),
    val valid: Boolean= "no".toBoolean(),
    val notes: String= "".toString(),
) {
    fun toDomainTermID() : DomainTermID{
        return DomainTermID(
            value.toString(),
            valid.toString(),
            notes.toString(),
        ) }
}
