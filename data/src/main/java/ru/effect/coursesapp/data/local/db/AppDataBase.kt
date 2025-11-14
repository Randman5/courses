package ru.effect.coursesapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.effect.coursesapp.data.local.db.dao.CourseDao
import ru.effect.coursesapp.data.local.db.entity.CourseEntity
import ru.effect.coursesapp.data.local.db.converters.DateConverter

@Database(entities = [CourseEntity::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
}