package sen.com.module.food.pages.foodDetails

import androidx.lifecycle.MutableLiveData
import sen.com.absraction.base.BaseViewModel
import sen.com.absraction.extentions.isTrue
import sen.com.module.food.model.FoodViewState
import sen.com.module.food.repositories.FoodRepository

/**
 * Created by korneliussendy on 02/03/20,
 * at 00.29.
 * My Application
 */
class VMFoodDetails : BaseViewModel() {
    private val repo = FoodRepository.instance
    val food: MutableLiveData<FoodViewState> = MutableLiveData()
    var id: String? = null

    fun getFood() {
        (food.value ?: FoodViewState()).apply {
            loading = true
            error = false
        }
        subscribe {
            repo.getItem(id!!).subscribe({ handleResult(it) }, { handleError(it) })
        }
    }

    private fun handleResult(result: FoodViewState) {
        food.value = (food.value ?: FoodViewState()).apply {
            loading = false
            error = false
            data = result.data
            result.local?.let { v -> this.local = v }
            result.live?.let { v -> this.live = v }
            result.completed = result.live.isTrue() && result.local.isTrue()
        }
    }

    private fun handleError(throwable: Throwable) {
        food.value = (food.value ?: FoodViewState()).apply {
            loading = false
            error = true
            errorMessage = throwable.localizedMessage
        }
    }

    fun refresh() {
        food.value = FoodViewState().apply {
            loading = true
            error = false
        }
        subscribe {
            repo.getRemoteItem(id!!).subscribe(
                { handleResult(it) },
                { handleError(it) })
        }
    }
}