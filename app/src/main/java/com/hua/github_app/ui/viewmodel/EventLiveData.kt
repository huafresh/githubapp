package com.hua.github_app.ui.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.hua.github_app.utils.LogUtil

/**
 * Created on 2022/8/14.
 *
 * Sometimes we just want to notify the UI of a certain change,
 * and the change is oneshot. This class can be used at this situation.
 *
 * @author hua
 */
class EventLiveData<T> : MutableLiveData<T>() {

    override fun setValue(value: T) {
        super.setValue(value)
        super.setValue(null)
    }

    override fun postValue(value: T) {
        super.postValue(value)
        super.setValue(null)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner) {
            if (it != null) {
                observer.onChanged(it)
            }
        }
    }

    override fun observeForever(observer: Observer<in T>) {
        super.observeForever() {
            if (it != null) {
                observer.onChanged(it)
            }
        }
    }
}