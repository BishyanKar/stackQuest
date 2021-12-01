package com.example.stackoverquestions.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stackoverquestions.data.localdb.daos.QuestionDao
import com.example.stackoverquestions.models.Question

@Database(version = 1, entities = [Question::class])
abstract class QuestionDatabase: RoomDatabase() {
    abstract fun questionDao(): QuestionDao
}