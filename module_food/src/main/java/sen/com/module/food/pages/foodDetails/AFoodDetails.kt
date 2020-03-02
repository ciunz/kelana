package sen.com.module.food.pages.foodDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.a_food_details.*
import sen.com.absraction.extentions.isTrue
import sen.com.absraction.extentions.loadLong
import sen.com.module.food.R
import sen.com.uicomponent.transitions.TextResize

/**
 * Created by korneliussendy on 01/03/20,
 * at 20.20.
 * My Application
 */
class AFoodDetails : AppCompatActivity() {
    private lateinit var vm: VMFoodDetails
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_food_details)
        setSupportActionBar(toolbar)
        TextResize().addTarget(tvTitle)
        intent.extras?.let {
            ivImage.setImageBitmap(intent?.extras?.getParcelable("imagebitmap"))
            tvTitle.text = intent?.extras?.getString("title")
            title = intent?.extras?.getString("title")
        }
        srl.setOnRefreshListener {
            srl.isRefreshing = false
            vm.refresh()
        }
        vm = ViewModelProvider(this).get(VMFoodDetails::class.java).apply {
            food.observe(this@AFoodDetails, Observer { vs ->
                tv.text = when {
                    vs?.error.isTrue() -> "ERROR"
                    vs?.completed.isTrue() -> "COMPLETED"
                    vs?.live.isTrue() -> "LIVE"
                    vs?.local.isTrue() -> "LOCAL"
                    else -> "LOADING"
                }

                vs.data?.let { food ->
                    ivImage.loadLong(food.image)
                    tvTitle.text = food.name
                }
            })
        }
        vm.id = intent?.extras?.getString("ID")
        vm.getFood()
    }
}