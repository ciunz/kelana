package sen.com.module.food.dataStores

import com.google.firebase.database.*
import io.reactivex.Observable
import io.reactivex.Single
import sen.com.module.food.model.Food

/**
 * Created by korneliussendy on 29/02/20,
 * at 01.34.
 * My Application
 */
class FoodRemoteDataStore(private val db: DatabaseReference) : FoodDataStore() {

    override fun getFood(id: String): Single<Food> = Single.create {
        db.child(id).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                it.onError(p0.toException())
            }

            override fun onDataChange(p0: DataSnapshot) {
                val v = p0.getValue(Food::class.java)
                it.onSuccess(v ?: throw NullPointerException())
            }
        })
    }

    override fun getList(): Observable<MutableList<Food>> = Observable.create { emitter ->
        db.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                emitter.onError(p0.toException())
            }

            override fun onDataChange(p0: DataSnapshot) {
                val arr = mutableListOf<Food>()
                p0.children.forEach { snapshot ->
                    snapshot.getValue(Food::class.java)?.let { arr.add(it) }
                }
                emitter.onNext(arr)
                emitter.onComplete()
            }
        })
    }

    override fun addFoods(list: MutableList<Food>?) {
    }

    override fun addFood(food: Food) {
    }

    companion object {
        val instance by lazy { FoodRemoteDataStore(FirebaseDatabase.getInstance().getReference("food")) }
    }
}