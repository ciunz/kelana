package sen.com.uicomponent.spannable

import android.graphics.Color
import android.os.SystemClock
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View

/**
 * Created by korneliussendy on 2020-02-11,
 * at 17:04.
 * Ajaib
 */
class ClickSpan(
    private var defaultInterval: Int = 1000,
    private val onSafeCLick: ((View) -> Unit)?
) : ClickableSpan() {
    private var lastTimeClicked: Long = 0
    private var isPressed = false
    override fun onClick(widget: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeCLick?.invoke(widget)
        widget.invalidate()
    }

    fun setPressed(isPressed: Boolean) {
        this.isPressed = isPressed
    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        ds.color = Color.parseColor("#3a83f9")
        ds.isUnderlineText = false
    }
}