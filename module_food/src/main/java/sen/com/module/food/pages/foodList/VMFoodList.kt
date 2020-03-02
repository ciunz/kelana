package sen.com.module.food.pages.foodList

import androidx.lifecycle.MutableLiveData
import sen.com.absraction.base.BaseViewModel
import sen.com.absraction.extentions.isTrue
import sen.com.module.food.model.FoodListViewState
import sen.com.module.food.repositories.FoodRepository

/**
 * Created by korneliussendy on 01/03/20,
 * at 13.44.
 * My Application
 */
class VMFoodList : BaseViewModel() {
    private val repo = FoodRepository.instance
    val foodList: MutableLiveData<FoodListViewState?> = MutableLiveData()
    fun loadFoodList() {
        foodList.value = (foodList.value ?: FoodListViewState()).apply {
            loading = true
            error = false
        }
        subscribe {
            repo.geList().subscribe(
                { handleResult(it) },
                { handleError(it) })
        }
    }

    fun refresh() {
        foodList.value = FoodListViewState().apply {
            loading = true
            error = false
        }
        subscribe {
            repo.getRemoteList().subscribe(
                { handleResult(it) },
                { handleError(it) })
        }
    }

    private fun handleResult(result: FoodListViewState) {
        foodList.value = (foodList.value ?: FoodListViewState()).apply {
            loading = false
            error = false
            data = result.data
            result.local?.let { v -> this.local = v }
            result.live?.let { v -> this.live = v }
            result.completed = result.live.isTrue() && result.local.isTrue()
        }
    }

    private fun handleError(throwable: Throwable) {
        foodList.value = (foodList.value ?: FoodListViewState()).apply {
            loading = false
            error = true
            errorMessage = throwable.localizedMessage
        }
    }
}
