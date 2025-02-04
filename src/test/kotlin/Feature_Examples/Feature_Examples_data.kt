package gherkinexecutor.Feature_Examples
data class NameValue(
    var iD: String = "",
    var value: String = "0",
)
data class Temperature(
    var f: String = "0",
    var c: String = "0",
    var notes: String = "",
)
{
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
