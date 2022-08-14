package com.hua.github_app.ui.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.hua.github_app.utils.LogUtil

/**
 * Created on 2022/8/14.
 *
 * Sometimes we just want to notify the UI of a certain change.
 * This change is one-time. This class can be used at this time.
 *
 * @author hua
 */
class EventLiveData<T> : MutableLiveData<T?>() {

    override fun setValue(value: T?) {
        super.setValue(value)
        super.setValue(null)
    }

    override fun postValue(value: T?) {
        super.postValue(value)
        super.setValue(null)
    }

    fun observeEvent(owner: LifecycleOwner, observer: Observer<T>) {
        super.observe(owner) {
            if (it != null) {
                observer.onChanged(it)
            }
        }
    }
}