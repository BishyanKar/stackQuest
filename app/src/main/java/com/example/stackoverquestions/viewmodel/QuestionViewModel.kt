package com.example.stackoverquestions.viewmodel

import androidx.lifecycle.*
import com.example.stackoverquestions.data.remoteDataSource.ApiResponse
import com.example.stackoverquestions.models.Question
import com.example.stackoverquestions.models.ReturnedObject
import com.example.stackoverquestions.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(private val questionRepository: QuestionRepository): ViewModel() {

    private val allQuestions: LiveData<List<Question>> = questionRepository.allQuestions.asLiveData()
    private val apiReturnedObject: LiveData<ApiResponse<ReturnedObject>> = questionRepository.apiReturnedObject

    @JvmName("getAllQuestionsVM")
    fun getAllQuestions(): LiveData<List<Question>>{
        return allQuestions
    }

    fun getResponse(): LiveData<ApiResponse<ReturnedObject>>{
        return apiReturnedObject
    }

    suspend fun insertAll(vararg questions: Question){
        questionRepository.insertAll(*questions)
    }

    suspend fun updateAll(vararg questions: Question){
        questionRepository.updateAll(*questions)
    }

    suspend fun deleteAll(){
        questionRepository.deleteAll()
    }

    suspend fun delete(question: Question){
        questionRepository.delete(question)
    }

}