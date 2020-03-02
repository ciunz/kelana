package sen.com.module.food.model

import sen.com.absraction.base.BaseViewState

/**
 * Created by korneliussendy on 29/02/20,
 * at 00.50.
 * My Application
 */
data class Food(
    var id: String? = null,
    var name: String? = null,
    var image: String? = null
)

class FoodViewState : BaseViewState<Food>()
class FoodListViewState : BaseViewState<MutableList<Food>>()