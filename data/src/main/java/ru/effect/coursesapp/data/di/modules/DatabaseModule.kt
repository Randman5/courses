package ru.effect.coursesapp.data.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.effect.coursesapp.data.local.db.AppDatabase
import ru.effect.coursesapp.data.local.db.dao.CourseDao
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "course_database")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideCourseDao(db: AppDatabase): CourseDao = db.courseDao()
}