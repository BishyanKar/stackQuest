package com.example.stackoverquestions.hilt.di

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.stackoverquestions.networkutil.NetworkChecker
import com.example.stackoverquestions.networkutil.NetworkState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideNetworkChecker(@ApplicationContext appContext: Context, networkState: NetworkState) = NetworkChecker(appContext,
        networkState.networkState as MutableLiveData<Boolean>
    )

    @Singleton
    @Provides
    fun provideNetworkState() = NetworkState(MutableLiveData<Boolean>())
}