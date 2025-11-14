package ru.effect.coursesapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.effect.coursesapp.data.local.db.dao.CourseDao
import ru.effect.coursesapp.data.local.db.entity.CourseEntity

@Database(entities = [CourseEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
}