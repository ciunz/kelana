package sen.com.absraction.base

/**
 * Created by korneliussendy on 01/03/20,
 * at 15.19.
 * My Application
 */
open class BaseViewState<Object>(
    var data: Object? = null,
    var loading: Boolean? = false,
    var live: Boolean? = false,
    var local:Boolean? = false,
    var completed: Boolean = false,
    var error: Boolean? = false,
    var errorMessage: String? = null
)