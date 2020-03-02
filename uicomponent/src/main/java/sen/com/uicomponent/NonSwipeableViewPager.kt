package sen.com.uicomponent

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager
import android.view.MotionEvent


class NonSwipeableViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {


    override fun onTouchEvent(event: MotionEvent): Boolean {
        return false
//        super.onTouchEvent(event)

    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return false
//        super.onInterceptTouchEvent(event)

    }
}