package sen.com.absraction.base

/**
 * Created by korneliussendy on 29/02/20,
 * at 01.36.
 * My Application
 */

abstract class BaseDataStore() {
    private var expiryMillis: Long? = null
    open fun expired() =
        expiryMillis != null && System.currentTimeMillis() > (expiryMillis ?: 0)

    open fun clear() {}
}