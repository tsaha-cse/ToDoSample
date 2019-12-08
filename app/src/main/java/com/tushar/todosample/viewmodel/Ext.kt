package com.tushar.todosample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> LiveData<T>.postVal(value: T) {
    (this as? MutableLiveData)?.postValue(value)
}
