package com.example.stackoverquestions.hilt.di

import android.content.Context
import androidx.room.Room
import com.example.stackoverquestions.data.localdb.QuestionDatabase
import com.example.stackoverquestions.data.localdb.daos.QuestionDao
import com.example.stackoverquestions.data.remoteDataSource.ApiService
import com.example.stackoverquestions.data.remoteDataSource.LiveDataCallAdapterFactory
import com.example.stackoverquestions.repository.QuestionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    private val BASE_URL = "https://api.stackexchange.com"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideQuestionDao(database: QuestionDatabase): QuestionDao {
        return database.questionDao()
    }

    @Singleton
    @Provides
    fun provideRepository(apiService: ApiService, questionDao: QuestionDao) = QuestionRepository(apiService,questionDao)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): QuestionDatabase {
        return Room.databaseBuilder(
            appContext,
            QuestionDatabase::class.java,
            "question_db"
        ).build()
    }
}