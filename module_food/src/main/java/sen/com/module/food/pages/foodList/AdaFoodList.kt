package sen.com.module.food.pages.foodList

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cell_food.view.*
import sen.com.absraction.base.BaseAdapter
import sen.com.absraction.extentions.loadCircle
import sen.com.absraction.utils.onClick
import sen.com.module.food.R
import sen.com.module.food.model.Food
import sen.com.uicomponent.transitions.TextResize

/**
 * Created by korneliussendy on 01/03/20,
 * at 18.03.
 * My Application
 */
class AdaFoodList : BaseAdapter<Food, AdaFoodList.VH>(R.layout.cell_food) {
    override fun createView(view: View, viewType: Int) =
        VH(view)

    val textResize by lazy { TextResize() }
    var onImageClicked: ((item: Food, imageView: ImageView, textView: TextView) -> Unit)? = null

    override fun onBindView(holder: VH, item: Food, pos: Int) {
        holder.apply {
            v.onClick { onImageClicked?.invoke(item, v.ivImage, v.tvName) }
            v.ivImage.loadCircle(item.image)
            v.tvName.text = item.name
        }
    }

    class VH(val v: View) : RecyclerView.ViewHolder(v)
}