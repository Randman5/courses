package ru.effect.coursesapp.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment


class MultiNavigationManager(
    private val activity: AppCompatActivity,
    private val containerId: Int
) {

    private val fm: FragmentManager get() = activity.supportFragmentManager
    private val navHosts = mutableMapOf<Int, NavHostFragment>()
    private var activeMenuId: Int? = null
    private var activeHost: NavHostFragment? = null
    private var defaultMId: Int? = null

    private val tabBackStack = ArrayDeque<Int>() // история вкладок

    fun setupNavHosts(menuGraphMap: Map<Int, Int>, defaultMenuId: Int) {
        menuGraphMap.forEach { (menuId, graphId) ->
            val tag = "nav_$graphId"
            val navHost = fm.findFragmentByTag(tag) as? NavHostFragment
                ?: NavHostFragment.create(graphId).also {
                    fm.beginTransaction()
                        .add(containerId, it, tag)
                        .hide(it)
                        .commitNow()
                }
            navHosts[menuId] = navHost
        }

        // показать дефолтную вкладку
        val defaultHost = navHosts[defaultMenuId]!!
        defaultMId = defaultMenuId
        fm.beginTransaction().show(defaultHost).commitNow()

        activeHost = defaultHost
        activeMenuId = defaultMenuId
        tabBackStack.add(defaultMenuId)
    }

    fun switchTo(menuItemId: Int) {
        if (menuItemId == activeMenuId) return

        val newHost = navHosts[menuItemId] ?: return
        val currentHost = activeHost ?: return

        fm.beginTransaction()
            .hide(currentHost)
            .show(newHost)
            .commit()

        // обновляем активный
        activeHost = newHost
        activeMenuId = menuItemId

        // обновляем стек вкладок
        tabBackStack.removeIf{it == menuItemId} // если уже есть — перемещаем в конец
        tabBackStack.add(menuItemId)
    }

    /**
     * Возвращает true, если back обработан внутри графа или между вкладками
     */
    fun handleBackPress(): Boolean {
        val currentNavController = activeHost?.navController

        // если можем вернуться внутри текущего графа
        if (currentNavController?.popBackStack() == true) return true

        // если можем вернуться на предыдущую вкладку
        if (tabBackStack.size > 1) {
            // убираем текущую
            tabBackStack.removeLast()
            val previousMenuId = tabBackStack.last()
            switchTo(previousMenuId)
            return true
        } else if (tabBackStack.size == 1 && tabBackStack.last() != defaultMId){
            //последний таб всегда должен быть дефолтным
            defaultMId?.let {
                activeMenuId = defaultMId
                activeHost?.let { host ->
                    fm.beginTransaction()
                        .hide(host)
                        .show(navHosts[activeMenuId]!!)
                        .commit()
                }
            }
            tabBackStack.removeLast()
            return true
        }

        // стек вкладок пуст — выходим из приложения
        return false
    }

    fun getActiveMenuId(): Int? = activeMenuId
}