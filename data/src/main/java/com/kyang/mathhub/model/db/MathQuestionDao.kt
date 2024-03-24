package com.kyang.mathhub.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kyang.mathhub.model.db.entity.MathQuestionEntity

@Dao
interface MathQuestionDao {

    @Query("SELECT * FROM math_questions")
    suspend fun getAll(): List<MathQuestionEntity>

    @Query("SELECT * FROM math_questions WHERE question LIKE :input")
    suspend fun getByQuestion(input: String): List<MathQuestionEntity>

    @Insert
    suspend fun insert(question: MathQuestionEntity)

    @Query("DELETE FROM math_questions")
    suspend fun deleteHistory()
}
