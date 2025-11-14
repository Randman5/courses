package ru.effect.coursesapp.di.domain

import dagger.Module
import dagger.Provides
import ru.effect.coursesapp.domain.repository.CoursesRepositoryLocal
import ru.effect.coursesapp.domain.repository.CoursesRepository
import ru.effect.coursesapp.domain.repository.SessionRepository
import ru.effect.coursesapp.domain.usecase.AddToFavoriteUseCase
import ru.effect.coursesapp.domain.usecase.AuthorizationUseCase
import ru.effect.coursesapp.domain.usecase.DeleteFromFavoriteUseCase
import ru.effect.coursesapp.domain.usecase.IsLoggedInUseCase
import ru.effect.coursesapp.domain.usecase.LoadCoursesLocalUseCase
import ru.effect.coursesapp.domain.usecase.LoadCoursesUseCase
import ru.effect.coursesapp.domain.usecase.ValidateEmailUseCase

@Module
class DomainModule {

    @Provides
    fun provideAuthorizationUseCase(sessionRepository: SessionRepository): AuthorizationUseCase {
        return AuthorizationUseCase(
            sessionRepository = sessionRepository
        )
    }

    @Provides
    fun provideIsLoggedInUseCase(sessionRepository: SessionRepository): IsLoggedInUseCase {
        return IsLoggedInUseCase(
            sessionRepository = sessionRepository
        )
    }

    @Provides
    fun provideValidateEmailUseCase(): ValidateEmailUseCase {
        return ValidateEmailUseCase()
    }

    @Provides
    fun provideLoadCoursesUseCase(
        coursesRepository: CoursesRepository,
        coursesRepositoryLocal: CoursesRepositoryLocal
    ): LoadCoursesUseCase {
        return LoadCoursesUseCase(coursesRepository, coursesRepositoryLocal)
    }

    @Provides
    fun provideAddToFavoriteUseCase(coursesRepositoryLocal: CoursesRepositoryLocal): AddToFavoriteUseCase {
        return AddToFavoriteUseCase(coursesRepositoryLocal)
    }

    @Provides
    fun provideDeleteFromFavoriteUseCase(coursesRepositoryLocal: CoursesRepositoryLocal): DeleteFromFavoriteUseCase {
        return DeleteFromFavoriteUseCase(coursesRepositoryLocal)
    }

    @Provides
    fun provideLoadCoursesLocalUseCase(coursesRepositoryLocal: CoursesRepositoryLocal): LoadCoursesLocalUseCase {
        return LoadCoursesLocalUseCase(coursesRepositoryLocal)
    }
}