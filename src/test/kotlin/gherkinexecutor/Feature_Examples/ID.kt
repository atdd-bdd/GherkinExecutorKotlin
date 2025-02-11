package gherkinexecutor.Feature_Examples

data class ID(val value: String) {

    init {
        if (this.value.length < 5 )
            throw Exception("Too short")
        if (this.value.length > 5)
            throw Exception("Too long")
        if (this.value.get(0) != 'Q')
            throw Exception("Must begin with Q")

    }
// Alternative validation method
//    fun isValid(): Boolean {
//        if (this.value.length < 5 )
//            return false
//        if (this.value.length > 5)
//            return false
//        if (this.value.get(0) != 'Q')
//            return false
//        return true
//    }

}
