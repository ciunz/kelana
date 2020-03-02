package sen.com.uicomponent.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.Transformation
import androidx.annotation.AnimRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sen.com.uicomponent.DividerItemDecorator
import sen.com.uicomponent.R
import sen.com.uicomponent.SpanningLinearLayoutManager
import sen.com.uicomponent.objects.SpotlightItem

/**
 * Created by korneliussendy on 2019-06-30,
 * at 11:36.
 * Ajaib
 */
object ViewUtils {
    fun visibleOrInvisible(conditionForVisible: Boolean, vararg views: View?) {
        views.forEach { it?.visibility = if (conditionForVisible) View.VISIBLE else View.INVISIBLE }
    }

    fun visibleOrGone(conditionForVisible: Boolean, vararg views: View?) {
        views.forEach { it?.visibility = if (conditionForVisible) View.VISIBLE else View.GONE }
    }

    fun invisible(vararg views: View?) =
        views.forEach { if (it != null) it.visibility = View.INVISIBLE }

    fun visible(vararg views: View?) =
        views.forEach { if (it != null) it.visibility = View.VISIBLE }

    fun gone(vararg views: View?) = views.forEach { if (it != null) it.visibility = View.GONE }

    fun enable(vararg views: View?) = views.forEach { if (it != null) it.isEnabled = true }

    fun disable(vararg views: View?) = views.forEach { if (it != null) it.isEnabled = false }

    fun enableIf(conditionForEnable: Boolean, vararg views: View?) {
        views.forEach { it?.isEnabled = conditionForEnable }
    }

    fun disableNestedScroll(vararg views: View?) {
        views.forEach { if (it != null) ViewCompat.setNestedScrollingEnabled(it, false) }
    }

    fun View.disableNestedScroll() = ViewCompat.setNestedScrollingEnabled(this, false)

    fun setupHorizontalRecyclerView(
        rv: RecyclerView,
        adapter: RecyclerView.Adapter<*>,
        divider: Boolean
    ) {
        val context = rv.context
        if (context == null) {
            Log.d("HorizontalRecyclerView", "NULL CONTEXT")
            return
        }
        val manager = LinearLayoutManager(rv.context ?: return, RecyclerView.HORIZONTAL, false)
        rv.layoutManager = manager
        rv.adapter = adapter
        if (divider) {
            rv.addItemDecoration(
                DividerItemDecoration(
                    rv.context,
                    manager.orientation
                )
            )
        }
    }

    fun setupSpanningHorizontalRecyclerView(
        rv: RecyclerView,
        adapter: RecyclerView.Adapter<*>
    ) {
        val context = rv.context
        if (context == null) {
            Log.d("HorizontalRecyclerView", "NULL CONTEXT")
            return
        }
        val manager = SpanningLinearLayoutManager(
            rv.context ?: return,
            RecyclerView.HORIZONTAL,
            false
        )
        rv.layoutManager = manager
        rv.adapter = adapter
    }

    fun setupHorizontalRecyclerView(rv: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        return setupHorizontalRecyclerView(rv, adapter, false)
    }

    fun setupRecyclerView(
        context: Context?,
        rv: RecyclerView,
        adapter: RecyclerView.Adapter<*>,
        divider: Boolean
    ): LinearLayoutManager {
        if (context == null) throw NullPointerException("empty context")
        val manager = LinearLayoutManager(context)
        rv.layoutManager = manager
        rv.adapter = adapter
        if (divider) {
            rv.addItemDecoration(
                DividerItemDecorator(
                    ContextCompat.getDrawable(rv.context, R.drawable.divider)
                )
            )
        }
        return manager
    }

    fun setupRecyclerView(rv: RecyclerView, adapter: RecyclerView.Adapter<*>): LinearLayoutManager {
        return setupRecyclerView(rv.context, rv, adapter, false)
    }

    fun setupRecyclerView(
        rv: RecyclerView,
        adapter: RecyclerView.Adapter<*>,
        divider: Boolean
    ): LinearLayoutManager {
        return setupRecyclerView(
            rv.context,
            rv,
            adapter,
            divider
        )
    }

    fun setupGridRecyclerView(
        rv: RecyclerView,
        adapter: RecyclerView.Adapter<*>,
        grid: Int = 3,
        spacingDP: Int = 0
    ): GridLayoutManager {
        val manager = GridLayoutManager(rv.context, grid)
        rv.layoutManager = manager
        rv.adapter = adapter
        rv.addItemDecoration(ItemOffsetDecoration(spacingDP))
        return manager
    }


    fun animate(context: Context?, v: View?, @AnimRes res: Int) {
        if (context == null || v == null) return
        v.startAnimation(AnimationUtils.loadAnimation(context, res))
    }

    fun slideUp(context: Context?, v: View?) {
        animate(context, v, R.anim.slide_up)
        gone(v)
    }

    fun slideDown(context: Context?, v: View?) {
        visible(v)
        animate(context, v, R.anim.slide_down)
    }

    fun expand(v: View, onAnim: ((started: Boolean) -> Unit)?) {
//        val h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        v.forceLayout()
        v.measure(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        val targetHeight = v.measuredHeight
        Log.d("====>", targetHeight.toString())
        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.layoutParams.height = 1
        v.visibility = View.VISIBLE
        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                v.layoutParams.height = if (interpolatedTime == 1f)
                    WindowManager.LayoutParams.WRAP_CONTENT
                else
                    (targetHeight * interpolatedTime).toInt()
                v.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }
        a.duration = (2 * targetHeight / v.context.resources.displayMetrics.density).toLong()
        a.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                onAnim?.invoke(false)
            }

            override fun onAnimationStart(animation: Animation?) {
                onAnim?.invoke(true)
            }

        })
        v.startAnimation(a)
    }

    fun expand(v: View) {
        expand(v, null)
    }

    fun collapse(v: View, onAnim: ((started: Boolean) -> Unit)?) {
        v.forceLayout()
        val initialHeight = v.measuredHeight
        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                if (interpolatedTime == 1f) {
                    v.visibility = View.GONE
                } else {
                    v.layoutParams.height =
                        initialHeight - (initialHeight * interpolatedTime).toInt()
                    v.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        // 1dp/ms
        a.duration = (2 * initialHeight / v.context.resources.displayMetrics.density).toLong()
        a.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                onAnim?.invoke(false)
            }

            override fun onAnimationStart(animation: Animation?) {
                onAnim?.invoke(true)
            }

        })
        v.startAnimation(a)
    }

    fun collapse(v: View) {
        collapse(v, null)
    }

    fun slideOutRight(v: View, onAnim: ((started: Boolean) -> Unit)? = null) {
        val initialWidth = v.measuredWidth

        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                if (interpolatedTime == 1f) {
                    v.visibility = View.GONE
                } else {
                    v.layoutParams.width =
                        initialWidth - (initialWidth * interpolatedTime).toInt()
                    v.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        // 1dp/ms
        a.duration = (2 * initialWidth / v.context.resources.displayMetrics.density).toLong()
        a.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                onAnim?.invoke(false)
            }

            override fun onAnimationStart(animation: Animation?) {
                onAnim?.invoke(true)
            }

        })
        v.startAnimation(a)
    }

    fun slideInRight(v: View, onAnim: ((started: Boolean) -> Unit)? = null) {
        v.measure(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        val initialWidth = v.measuredWidth
        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.layoutParams.width = 1
        v.visibility = View.VISIBLE
        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                v.layoutParams.width = if (interpolatedTime == 1f)
                    WindowManager.LayoutParams.WRAP_CONTENT
                else
                    (initialWidth * interpolatedTime).toInt()
                v.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }
        a.duration = (2 * initialWidth / v.context.resources.displayMetrics.density).toLong()
        a.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                onAnim?.invoke(false)
            }

            override fun onAnimationStart(animation: Animation?) {
                onAnim?.invoke(true)
            }

        })
        v.startAnimation(a)
    }

    fun toggleHorizontalRight(v: View?, onAnim: ((started: Boolean) -> Unit)? = null) {
        if (v == null) return
        if (v.visibility == View.VISIBLE) slideOutRight(v, onAnim)
        else slideInRight(v, onAnim)
    }

    fun toggle(v: View?, animate: Boolean = true, onAnim: ((started: Boolean) -> Unit)? = null) {
        if (v == null) return
        if (v.visibility == View.VISIBLE) if (animate) collapse(v, onAnim) else {
            gone(v)
            onAnim?.invoke(true)
            onAnim?.invoke(false)
        }
        else {
            if (animate) expand(v, onAnim)
            else {
                visible(v)
                onAnim?.invoke(true)
                onAnim?.invoke(false)
            }
        }
    }

    fun toggle(v: View?, onAnim: ((started: Boolean) -> Unit)? = null) {
        if (v == null) return
        if (v.visibility == View.VISIBLE) collapse(v, onAnim)
        else expand(v, onAnim)
    }
}
