package ru.effect.coursesapp.data.local.SharedPreferences.session

import android.content.Context
import android.content.SharedPreferences
import ru.effect.coursesapp.data.Dto.UserDto

class SessionStorageImpl(context: Context) : SessionStorage {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("session_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_EMAIL = "user_email"
        private const val KEY_PASSWORD = "user_password"
    }

    override fun saveUser(userDto: UserDto) {
        prefs.edit().apply {
            putString(KEY_EMAIL, userDto.email)
            putString(KEY_PASSWORD, userDto.password)
            apply()
        }
    }

    override fun getUser(): UserDto? {
        val email = prefs.getString(KEY_EMAIL, null)
        val password = prefs.getString(KEY_PASSWORD, null)
        return if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            return UserDto(email, password)
        } else null
    }

    override fun hasUser(): Boolean {
        return prefs.contains(KEY_EMAIL) && prefs.contains(KEY_PASSWORD)
    }

    override fun clearUser() {
        prefs.edit().clear().apply()
    }
}