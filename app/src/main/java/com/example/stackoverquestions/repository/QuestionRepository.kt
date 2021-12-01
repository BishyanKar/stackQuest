package com.example.stackoverquestions.repository

import androidx.lifecycle.LiveData
import com.example.stackoverquestions.data.localdb.daos.QuestionDao
import com.example.stackoverquestions.data.remoteDataSource.ApiResponse
import com.example.stackoverquestions.data.remoteDataSource.ApiService
import com.example.stackoverquestions.models.Question
import com.example.stackoverquestions.models.ReturnedObject
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val apiService: ApiService, private val questionDao: QuestionDao) {

    val allQuestions: Flow<List<Question>> = questionDao.getAll()
    val apiReturnedObject: LiveData<ApiResponse<ReturnedObject>> = apiService.getResponse()

    suspend fun insertAll(vararg questions: Question){
        questionDao.insertAll(*questions)
    }

    suspend fun updateAll(vararg questions: Question){
        questionDao.updateAll(*questions)
    }

    suspend fun deleteAll(){
        questionDao.deleteAll()
    }

    suspend fun delete(question: Question){
        questionDao.delete(question)
    }
}