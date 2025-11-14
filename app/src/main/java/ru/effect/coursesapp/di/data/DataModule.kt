package ru.effect.coursesapp.di.data;

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.effect.coursesapp.data.api.ApiService
import ru.effect.coursesapp.data.mapper.CourseMapper
import ru.effect.coursesapp.data.mapper.UserMapper
import ru.effect.coursesapp.data.repository.CoursesRepositoryImpl
import ru.effect.coursesapp.data.repository.SessionRepositoryImpl
import ru.effect.coursesapp.data.local.SharedPreferences.session.SessionStorage
import ru.effect.coursesapp.data.local.SharedPreferences.session.SessionStorageImpl
import ru.effect.coursesapp.data.local.db.dao.CourseDao
import ru.effect.coursesapp.data.repository.CourseRepositoryLocalImpl
import ru.effect.coursesapp.domain.repository.CoursesRepository
import ru.effect.coursesapp.domain.repository.CoursesRepositoryLocal
import ru.effect.coursesapp.domain.repository.SessionRepository

@Module
class DataModule {

    @Provides
    fun provideSessionRepository(context: SessionStorage, userMapper: UserMapper): SessionRepository {
        return SessionRepositoryImpl(
            context,
            userMapper
        )
    }

    @Provides
    fun provideCoursesRepository(apiService: ApiService, courseMapper: CourseMapper): CoursesRepository {
        return CoursesRepositoryImpl(
            apiService,
            courseMapper
        )
    }

    @Provides
    fun provideCoursesRepositoryLocal(courseDao: CourseDao, courseMapper: CourseMapper): CoursesRepositoryLocal {
        return CourseRepositoryLocalImpl(
            courseDao,
            courseMapper
        )
    }

    @Provides
    fun provideSessionStorage(context: Context): SessionStorage {
        return SessionStorageImpl(context)
    }

    @Provides
    fun provideUserMapper(): UserMapper {
        return UserMapper()
    }

    @Provides
    fun provideCoursesMapper(): CourseMapper {
        return CourseMapper()
    }
}
