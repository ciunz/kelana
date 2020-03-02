package sen.com.absraction.extentions

/**
 * Created by korneliussendy on 01/03/20,
 * at 19.02.
 * My Application
 */

fun Boolean?.isTrue() = isReallyTrue()

fun Boolean?.isReallyTrue() = this != null && this

fun Boolean?.isFalse() = isReallyFalse()

fun Boolean?.isReallyFalse() = this != null && this