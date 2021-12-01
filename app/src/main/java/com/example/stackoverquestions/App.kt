package com.example.stackoverquestions

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import com.example.stackoverquestions.networkutil.NetworkChecker
import com.example.stackoverquestions.networkutil.NetworkState
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class App: Application() {

    @Inject lateinit var networkChecker: NetworkChecker

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
        networkChecker.registerNetworkCallBack()
    }
}