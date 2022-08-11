package com.hua.github_app.base

import androidx.lifecycle.*
import com.hua.github_app.utils.LogUtil
import kotlinx.coroutines.*

/**
 * Created on 2022/8/9.
 *
 * @author hua
 */
open class BaseViewModel : ViewModel() {

    companion object {
        private const val TAG = "BaseViewModel"
    }

    private val _progressDialogConfig = MutableLiveData<ProgressDialogConfig>()
    val progressDialogConfig: LiveData<ProgressDialogConfig> = _progressDialogConfig

    @JvmOverloads
    protected fun showProgressDialog(message: String? = null) {
        _progressDialogConfig.postValue(ProgressDialogConfig(true).apply {
            this.message = message
        })
    }

    protected fun dismissProgressDialog() {
        _progressDialogConfig.postValue(ProgressDialogConfig(false))
    }

    protected fun launchMain(
        block: suspend CoroutineScope.() -> Unit,
        error: ((Throwable) -> Unit)? = null
    ): Job {
        val handler = CoroutineExceptionHandler { _, e ->
            LogUtil.e(TAG, "launchUI: exception", e)
            error?.invoke(e)
        }
        return viewModelScope.launch(Dispatchers.Main.immediate + handler) {
            block.invoke(this)
        }
    }

    protected fun launchIO(
        block: suspend CoroutineScope.() -> Unit,
        error: ((Throwable) -> Unit)? = null
    ): Job {
        val handler = CoroutineExceptionHandler { _, e ->
            LogUtil.e(TAG, "[launch] exception: ", e)
            error?.invoke(e)
        }
        return viewModelScope.launch(Dispatchers.IO + handler) {
            block.invoke(this)
        }
    }
}