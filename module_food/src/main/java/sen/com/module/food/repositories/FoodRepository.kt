package sen.com.module.food.repositories

import io.reactivex.Observable
import io.reactivex.Single
import sen.com.absraction.base.BaseRepository
import sen.com.module.food.dataStores.FoodDataStore
import sen.com.module.food.dataStores.FoodLocalDataStore
import sen.com.module.food.dataStores.FoodRemoteDataStore
import sen.com.module.food.model.FoodListViewState
import sen.com.module.food.model.FoodViewState

/**
 * Created by korneliussendy on 29/02/20,
 * at 01.12.
 * My Application
 */
class FoodRepository : BaseRepository<FoodDataStore>() {
    fun geList(): Observable<FoodListViewState> {
        val cached = localDataStore?.getList()
        val cacheExpired = localDataStore?.expired() ?: true
        return if (cached != null && !cacheExpired) getLocalList()
        else getRemoteList()
    }

    private fun getLocalList(forceUpdate: Boolean = false) =
        localDataStore?.getList()!!
            .flatMap {
                if (it.isNullOrEmpty()) getRemoteList()
                else Observable.just(FoodListViewState().apply {
                    data = it
                    local = true
                })
            }.onErrorResumeNext(getRemoteList())

    fun getRemoteList(): Observable<FoodListViewState> = remoteDataStore?.getList()!!.map {
        localDataStore?.addFoods(it)
        FoodListViewState().apply {
            data = it
            live = true
        }
    }

    fun getItem(id: String): Single<FoodViewState> {
        val cached = localDataStore?.getFood(id)
        val cacheExpired = localDataStore?.expired() ?: true
        return if (cached != null && !cacheExpired) getLocalItem(id)
        else getRemoteItem(id)
    }

    fun getLocalItem(id: String) = localDataStore?.getFood(id)!!
        .flatMap {
            Single.just(FoodViewState().apply {
                data = it
                local = true
            })
        }.onErrorResumeNext(getRemoteItem(id))

    fun getRemoteItem(id: String) = remoteDataStore?.getFood(id)!!
        .map {
            localDataStore?.addFood(it)
            FoodViewState().apply {
                data = it
                local = false
                live = true
            }
        }

    companion object {
        val instance by lazy {
            FoodRepository().apply {
                init(FoodLocalDataStore.instance, FoodRemoteDataStore.instance)
            }
        }
    }
}