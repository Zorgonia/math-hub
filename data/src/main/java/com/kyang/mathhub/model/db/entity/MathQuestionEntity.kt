package com.kyang.mathhub.model.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "math_questions")
data class MathQuestionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "question") val question: String,
    @ColumnInfo(name = "date") val timestamp: String,
    @ColumnInfo(name = "correct") val correct: Boolean,
    @ColumnInfo(name = "user_answer") val userAnswer: String,
    @ColumnInfo(name = "time_used") val timeUsed: Int,
    @ColumnInfo(name = "correct_answer") val correctAnswer: String,
)
