package sen.com.module.food.pages.foodList

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.a_food_list.*
import sen.com.absraction.extentions.isTrue
import sen.com.absraction.extentions.setupRecyclerView
import sen.com.module.food.R
import sen.com.module.food.pages.foodDetails.AFoodDetails


/**
 * Created by korneliussendy on 01/03/20,
 * at 12.12.
 * My Application
 */
class AFoodList : AppCompatActivity() {
    lateinit var vm: VMFoodList
    private val adapter by lazy { AdaFoodList() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_food_list)
        setupRecyclerView(rvList, adapter)
        adapter.onImageClicked = { item, imageView, textView ->
            val option = ActivityOptionsCompat
                .makeSceneTransitionAnimation(
                    this,
                    Pair<View, String>(imageView, "foodImage"),
                    Pair<View, String>(textView, "foodName")
                )
            val intent = Intent(this, AFoodDetails::class.java)
            imageView.buildDrawingCache()
            val image: Bitmap = Bitmap.createScaledBitmap(imageView.drawingCache, 300, 300, true)
            val extras = Bundle()
            extras.putParcelable("imagebitmap", image)
            extras.putString("title", item.name)
            extras.putString("ID", item.id)
            intent.putExtras(extras)
//            startActivity(
//                Router.FOOD_DETAILS,
//                option.toBundle()?.apply { putParcelable("imagebitmap", image) })
            startActivity(intent, option.toBundle())
        }
        srl.setOnRefreshListener {
            adapter.clear()
            vm.refresh()
            srl.isRefreshing = false
        }
        vm = ViewModelProvider(this).get(VMFoodList::class.java).apply {
            foodList.observe(this@AFoodList, Observer { vs ->
                tv.text = when {
                    vs?.error.isTrue() -> "ERROR"
                    vs?.completed.isTrue() -> "COMPLETED"
                    vs?.live.isTrue() -> "LIVE"
                    vs?.local.isTrue() -> "LOCAL"
                    else -> "LOADING"
                }
                when {
                    vs?.loading.isTrue() -> adapter.showLoader()
                    vs?.error.isTrue() -> adapter.showError(
                        vs?.errorMessage,
                        "Retry"
                    ) { vm.loadFoodList() }
                    else -> vs?.let {
                        adapter.setItems(vs.data?.let { it1 -> ArrayList(it1) })
                        adapter.notifyDataSetChanged()
                    }
                }
            })
        }
        vm.loadFoodList()
    }
}
