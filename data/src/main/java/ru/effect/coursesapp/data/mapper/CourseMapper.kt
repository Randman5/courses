package ru.effect.coursesapp.data.mapper
import ru.effect.coursesapp.data.Dto.CourseDto
import ru.effect.coursesapp.data.local.db.entity.CourseEntity
import ru.effect.coursesapp.domain.models.CourseModel

class CourseMapper {

    fun toDomain(dto: CourseDto): CourseModel {
        return CourseModel(
            id = dto.id,
            title = dto.title,
            text = dto.text,
            price = dto.price,
            rate = dto.rate,
            startDate = dto.startDate,
            hasLike = dto.hasLike,
            publishDate = dto.publishDate
        )
    }

    fun fromEntity(entity: CourseEntity): CourseModel = CourseModel(
        id = entity.id,
        title = entity.title,
        text = entity.text,
        price = entity.price,
        rate = entity.rate,
        startDate = entity.startDate,
        hasLike = entity.hasLike,
        publishDate = entity.publishDate
    )

    // Domain Model -> Entity
    fun toEntity(model: CourseModel): CourseEntity = CourseEntity(
        id = model.id,
        title = model.title,
        text = model.text,
        price = model.price,
        rate = model.rate,
        startDate = model.startDate,
        hasLike = model.hasLike,
        publishDate = model.publishDate
    )
}