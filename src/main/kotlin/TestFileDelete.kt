import java.io.File
import java.io.FileWriter

class TestFileDelete {
    fun delete() {
        val dir = "src\\test\\kotlin\\"
        val filename = dir + "glue.tmp"
        println(filename)
        var glue_template_file = FileWriter(filename)
        glue_template_file.close()
        println("File exists " + File(filename).exists())
        println("Delete file " + File(filename).delete())

    }

    }


fun main(){
    TestFileDelete().delete()
}