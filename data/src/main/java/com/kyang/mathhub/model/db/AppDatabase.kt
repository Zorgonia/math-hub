package com.kyang.mathhub.model.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MathQuestionEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun mathQuestionDao(): MathQuestionDao
}
