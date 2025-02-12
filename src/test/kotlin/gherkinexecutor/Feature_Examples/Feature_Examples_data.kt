package gherkinexecutor.Feature_Examples
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
