package Feature_Examples

data class ID(val value: String) {
    fun isValid(): Boolean {
        if (this.value.length < 5 )
            return false
        if (this.value.length > 6)
            return false
        if (this.value.get(0) != 'Q')
            return false
        return true
    }

}
