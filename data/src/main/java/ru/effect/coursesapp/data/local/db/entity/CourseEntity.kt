package ru.effect.coursesapp.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "courses")
data class CourseEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: Date,
    var hasLike: Boolean,
    val publishDate: Date
)