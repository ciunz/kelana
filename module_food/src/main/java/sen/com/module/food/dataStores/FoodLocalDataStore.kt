package sen.com.module.food.dataStores

import io.reactivex.Observable
import io.reactivex.Single
import sen.com.module.food.model.Food
import java.util.concurrent.TimeUnit

/**
 * Created by korneliussendy on 29/02/20,
 * at 00.59.
 * My Application
 */
class FoodLocalDataStore : FoodDataStore() {
    private var caches = mutableListOf<Food>()
    override fun getFood(id: String): Single<Food> = Single.create { emitter ->
        val r = caches.find { it.id == id }
        emitter.onSuccess(r!!)
    }

    override fun getList(): Observable<MutableList<Food>> =
        Observable.just(caches)

    override fun addFoods(list: MutableList<Food>?) {
        updateExpiry(TimeUnit.HOURS, 6)
        list?.let { caches = it }
    }

    override fun addFood(food: Food) {
        caches.find { it.id == food.id }?.apply {
            this.id = food.id
            this.image = food.image
            this.name = food.name
        }
    }

    companion object {
        val instance by lazy { FoodLocalDataStore() }
    }
}