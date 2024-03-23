package com.kyang.mathhub.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "math_questions")
data class MathQuestionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "question") val question: String,
    @ColumnInfo(name = "date") val timestamp: String,
    @ColumnInfo(name = "correct") val correct: Boolean
)
