package com.example.stackoverquestions.data.localdb.daos

import androidx.room.*
import com.example.stackoverquestions.models.Question
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg questions: Question)

    @Update
    suspend fun updateAll(vararg questions: Question)

    @Delete
    suspend fun delete(question: Question)

    @Query("delete from question")
    suspend fun deleteAll()

    @Query("select * from question")
    fun getAll(): Flow<List<Question>>

    @Query("select * from question where questionId = :id")
    fun getQuestionById(id: Int): Flow<Question>
}