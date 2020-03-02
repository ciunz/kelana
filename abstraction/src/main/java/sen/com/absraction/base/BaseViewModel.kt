package sen.com.absraction.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.launch

/**
 * Created by korneliussendy on 01/03/20,
 * at 14.02.
 * My Application
 */

open class BaseViewModel : ViewModel() {
    val TAG = this.javaClass.simpleName
    private val disposable = CompositeDisposable()
    fun subscribe(job: () -> Disposable?) {
        job()?.let { disposable.add(job()!!) }
    }
}