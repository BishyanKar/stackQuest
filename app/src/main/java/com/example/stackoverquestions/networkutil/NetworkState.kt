package com.example.stackoverquestions.networkutil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class NetworkState(private val _networkState: MutableLiveData<Boolean>){
    val networkState: LiveData<Boolean>
        get() = _networkState
}