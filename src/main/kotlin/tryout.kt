import java.util.*

class tryout(val name: String, val name1 : String, val n : String) {


    fun print() {
        println("name " + name)
        println("name1 " + name1)
        println("n "+ n)
    }
}
fun convert(in1: tryout): tryoutInt {
    return tryoutInt(Integer( in1.name),Integer(in1.name1),
        Date(in1.n))
}

fun convertBack(in1: tryoutInt): tryout {
    return tryout(in1.name.toString(), in1.name1.toString(),
        in1.n.toString())
}
class tryoutInt(val name: Integer, val name1 : Integer, val n : Date) {


    fun print() {
        println("name " + name)
        println("name1 " + name1)
        println("n "+ n)
    }
}
fun main(args: Array<String>) {
    val v = tryout("1","00","1/2/1999")
    val int1 = convert(v)
    val w = convertBack(int1)
    int1.print()

}
