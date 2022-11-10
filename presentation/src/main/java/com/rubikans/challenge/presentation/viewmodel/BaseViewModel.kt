package com.rubikans.challenge.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseViewModel<S> : ViewModel() {

    abstract val stateObservable: MutableLiveData<S>

    protected open fun publishState(state: S) {
        stateObservable.postValue(state)
    }

    abstract val coroutineExceptionHandler: CoroutineExceptionHandler

    protected fun launchCoroutine(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(coroutineExceptionHandler) {
            block()
        }
    }
}