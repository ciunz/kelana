package sen.com.kelana

import androidx.multidex.MultiDexApplication
import sen.com.module.food.di.DaggerFoodComponent

/**
 * Created by korneliussendy on 01/03/20,
 * at 10.43.
 * My Application
 */

class MyApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        DaggerFoodComponent.builder().build()
    }
}