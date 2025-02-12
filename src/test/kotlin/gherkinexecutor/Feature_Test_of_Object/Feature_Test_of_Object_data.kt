package gherkinexecutor.Feature_Test_of_Object

import java.io.File
import java.net.URL
import java.util.regex.Pattern

data class ContainsLibraryClass(
    val aURL: String = "http://ken-pugh.com",
    val aFile: String = "anyfile.txt",
    val aPattern: String = "a.*b",
)
{
    fun toContainsLibraryClassInternal() : ContainsLibraryClassInternal{
        return ContainsLibraryClassInternal(
            URL(aURL),
            File(aFile),
            aPattern.toPattern(),
        ) }
}
data class ContainsLibraryClassInternal(
    val aURL: URL= URL("http://ken-pugh.com"),
//    val aURL: URL= "http://ken-pugh.com".toURL(),
    val aFile: File= File("anyfile.txt"),
//    val aFile: File= "anyfile.txt".toFile(),
    val aPattern: Pattern= "a.*b".toPattern(),
) {

    // Created, but not used
//    fun toContainsLibraryClass() : ContainsLibraryClass{
//        return ContainsLibraryClass(
//            aURL.toString(),
//            aFile.toString(),
//            aPattern.toString(),
//        ) }
}
