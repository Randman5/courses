package ru.effect.coursesapp.di.app

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.effect.coursesapp.domain.repository.CoursesRepository
import ru.effect.coursesapp.domain.usecase.AddToFavoriteUseCase
import ru.effect.coursesapp.domain.usecase.AuthorizationUseCase
import ru.effect.coursesapp.domain.usecase.DeleteFromFavoriteUseCase
import ru.effect.coursesapp.domain.usecase.IsLoggedInUseCase
import ru.effect.coursesapp.domain.usecase.LoadCoursesLocalUseCase
import ru.effect.coursesapp.domain.usecase.LoadCoursesUseCase
import ru.effect.coursesapp.domain.usecase.ValidateEmailUseCase
import ru.effect.coursesapp.ui.presentation.favorite.FavoriteFragmentViewModelFactory
import ru.effect.coursesapp.ui.presentation.home.HomeFragmentViewModelFactory
import ru.effect.coursesapp.ui.presentation.login.LoginFragmentViewModelFactory

@Module
class AppModule(val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideLoginViewModelFactory(
        authorizationUseCase: AuthorizationUseCase,
        validateEmailUseCase: ValidateEmailUseCase,
        isLoggedInUseCase: IsLoggedInUseCase
    ): LoginFragmentViewModelFactory {
        return LoginFragmentViewModelFactory(
            authorizationUseCase,
            validateEmailUseCase,
            isLoggedInUseCase
        )
    }

    @Provides
    fun provideHomeViewModelFactory(
        loadCoursesUseCase: LoadCoursesUseCase,
        addToFavoriteUseCase: AddToFavoriteUseCase,
        deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase
    ): HomeFragmentViewModelFactory {
        return HomeFragmentViewModelFactory(loadCoursesUseCase, addToFavoriteUseCase, deleteFromFavoriteUseCase)
    }

    @Provides
    fun provideFavoriteViewModelFactory(
        loadCoursesLocalUseCase: LoadCoursesLocalUseCase,
        deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase
    ): FavoriteFragmentViewModelFactory {
        return FavoriteFragmentViewModelFactory(loadCoursesLocalUseCase, deleteFromFavoriteUseCase)
    }
}