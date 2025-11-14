package ru.effect.coursesapp.app

import android.app.Application
import ru.effect.coursesapp.di.AppComponent
import ru.effect.coursesapp.di.DaggerAppComponent
import ru.effect.coursesapp.di.app.AppModule

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(context = this))
            .build()
    }
}