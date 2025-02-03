import java.util.*

class tryout(val name: String, val name1 : String, val n : String) {


    fun print() {
        println("name " + name)
        println("name1 " + name1)
        println("n "+ n)
    }
    fun convert(): tryoutInt {
        return tryoutInt(Integer( this.name),Integer(this.name1),
            Date(this.n))
    }

}


class tryoutInt(val name: Integer, val name1 : Integer, val n : Date) {

    fun convert(): tryout {
        return tryout(this.name.toString(), this.name1.toString(),
            this.n.toString())
    }
    fun print() {
        println("name " + name)
        println("name1 " + name1)
        println("n "+ n)
    }
}
fun main(args: Array<String>) {
    val v = tryout("1","00","1/2/1999")
    v.print()
    val int1 = v.convert()
    int1.print()
    val w = int1.convert()
    w.print()
}
