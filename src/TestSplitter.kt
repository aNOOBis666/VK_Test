import solution.StringConst
import solution.splitMessageExtension

fun main() {
        val outPutShort = StringConst.SHORT_MESSAGE.value.splitMessageExtension(maxLengthMessage = 10)
        println("result: $outPutShort")
        val outPutMiddle = StringConst.MIDDLE_MESSAGE.value.splitMessageExtension(maxLengthMessage = 10)
        println("result: $outPutMiddle")
        val outPutLong = StringConst.LONG_MESSAGE.value.splitMessageExtension(maxLengthMessage = 50)
        println("result: $outPutLong")
    }