package com.example.stackoverquestions.data.remoteDataSource


import androidx.lifecycle.LiveData
import com.example.stackoverquestions.models.ReturnedObject
import retrofit2.Call

import retrofit2.http.GET

interface ApiService {

    @GET("/2.2/questions?key=ZiXCZbWaOwnDgpVT9Hx8IA((&order=desc&sort=activity&site=stackoverflow")
    fun getResponse(): LiveData<ApiResponse<ReturnedObject>>
}