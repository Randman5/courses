package ru.effect.coursesapp.di

import dagger.Component
import ru.effect.coursesapp.data.di.modules.DatabaseModule
import ru.effect.coursesapp.data.di.modules.NetworkModule
import ru.effect.coursesapp.di.app.AppModule
import ru.effect.coursesapp.di.data.DataModule
import ru.effect.coursesapp.di.domain.DomainModule
import ru.effect.coursesapp.ui.presentation.favorite.FavoriteFragment
import ru.effect.coursesapp.ui.presentation.home.HomeFragment
import ru.effect.coursesapp.ui.presentation.login.LoginFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, DomainModule::class, NetworkModule::class, DatabaseModule::class])
interface AppComponent {
    fun inject(loginFragment: LoginFragment)
    fun inject(loginFragment: HomeFragment)
    fun inject(favoriteFragment: FavoriteFragment)
}