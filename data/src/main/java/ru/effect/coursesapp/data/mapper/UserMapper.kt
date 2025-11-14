package ru.effect.coursesapp.data.mapper

import ru.effect.coursesapp.data.Dto.UserDto
import ru.effect.coursesapp.domain.models.UserModel

class UserMapper {

    fun toEntity(dto: UserDto): UserModel {
        return UserModel(
            email = dto.email,
            password = dto.password
        )
    }

    fun toDto(entity: UserModel): UserDto {
        return UserDto(
            email = entity.email,
            password = entity.password
        )
    }
}