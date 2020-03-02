package sen.com.uicomponent

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import androidx.viewpager.widget.ViewPager
import java.util.*

class SliderTimer(
    private val activity: AppCompatActivity?,
    private val viewPager: ViewPager
) : TimerTask() {
    override fun run() {
        activity?.runOnUiThread {
            if (viewPager.currentItem < viewPager.size - 1) {
                viewPager.setCurrentItem(viewPager.currentItem + 1)
            } else {
                viewPager.setCurrentItem(0)
            }
        }
    }
}