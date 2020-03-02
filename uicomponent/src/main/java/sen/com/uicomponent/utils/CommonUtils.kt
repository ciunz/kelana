package sen.com.uicomponent.utils

import sen.com.uicomponent.R

/**
 * Created by korneliussendy on 2020-02-19,
 * at 17:48.
 * Ajaib
 */
object CommonUtils {
    fun getNumberColor(value: Double?) = when {
        (value == null) -> R.color.text_secondary
        (value > 0) -> R.color.greenPrimary
        (value < 0) -> R.color.redPrimary
        else -> R.color.text_secondary
    }
}