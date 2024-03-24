package com.kyang.mathhub.model.db

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideMathQuestionDao(appDatabase: AppDatabase): MathQuestionDao {
        return appDatabase.mathQuestionDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        //or inMemoryDatabaseBuilder
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "MathHub"
        ).build()
    }

    // not required till production deploy (technically)
    private val MIGRATION_1_2 = object: Migration(1,2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE math_questions ADD COLUMN user_answer TEXT NOT NULL DEFAULT 0")
            db.execSQL("ALTER TABLE math_questions ADD COLUMN time_used INTEGER NOT NULL DEFAULT 1")
            db.execSQL("ALTER TABLE math_questions ADD COLUMN correct_answer TEXT NOT NULL DEFAULT 0")
        }
    }
}
