package sen.com.absraction.utils

import android.os.SystemClock
import android.view.View

/**
 * Created by korneliussendy on 2019-07-04,
 * at 20:23.
 * Ajaib
 */
class SafeClickListener(
    private var defaultInterval: Int = 1000,
    private val onSafeCLick: (View) -> Unit
) : View.OnClickListener {
    private var lastTimeClicked: Long = 0
    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeCLick(v)
    }
}

fun View.onClick(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun View.onClick(interval: Int, onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener(interval) {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}