import solution.StringConst
import solution.splitMessageExtension

fun main() {
        val outPutShort = StringConst.SHORT_MESSAGE.value.splitMessageExtension(maxLengthMessage = 10)
        println("result: $outPutShort")
//      result: [Lorem]
        val outPutMiddle = StringConst.MIDDLE_MESSAGE.value.splitMessageExtension(maxLengthMessage = 10)
        println("result: $outPutMiddle")
//      result: [Lorem 1/3, ipsum 2/3, dolor 3/3]
        val outPutLong = StringConst.LONG_MESSAGE.value.splitMessageExtension(maxLengthMessage = 50)
        println("result: $outPutLong")
//      result: [Lorem ipsum dolor sit amet, consectetur adipiscing 1/5, elit, sed do eiusmod tempor incididunt ut labore et 2/5, dolore magna aliqua. Ut enim ad minim veniam, quis 3/5, nostrud exercitation ullamco laborious nisi ut 4/5, aliquip ex ea commodo consequat. 5/5]

    }
