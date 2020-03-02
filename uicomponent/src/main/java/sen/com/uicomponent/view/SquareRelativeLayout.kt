package sen.com.uicomponent.view

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout


class SquareRelativeLayout : RelativeLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(measuredWidth, measuredWidth)
//        if (widthMeasureSpec > heightMeasureSpec)
//            super.onMeasure(widthMeasureSpec, widthMeasureSpec)
//        else
//            super.onMeasure(heightMeasureSpec, heightMeasureSpec)
    }
}