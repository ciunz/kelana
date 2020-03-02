package sen.com.module.food.dataStores

import io.reactivex.Observable
import io.reactivex.Single
import sen.com.absraction.base.BaseDataStore
import sen.com.module.food.model.Food
import java.util.concurrent.TimeUnit

/**
 * Created by korneliussendy on 29/02/20,
 * at 00.53.
 * My Application
 */
abstract class FoodDataStore : BaseDataStore() {
    private var expiryMillis: Long? = null
    override fun expired() =
        expiryMillis != null && System.currentTimeMillis() > (expiryMillis ?: 0)

    protected fun updateExpiry(timeUnit: TimeUnit, time: Long) {
        expiryMillis = System.currentTimeMillis() + timeUnit.toMillis(time)
    }

    open fun cached() = false

    abstract fun getFood(id: String): Single<Food>
    abstract fun getList(): Observable<MutableList<Food>>
    abstract fun addFoods(list: MutableList<Food>?)
    abstract fun addFood(food: Food)
}