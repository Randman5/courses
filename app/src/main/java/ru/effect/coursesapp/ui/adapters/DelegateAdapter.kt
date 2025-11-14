package ru.effect.coursesapp.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface DelegateAdapter<T> {
    fun isForViewType(items: List<*>, position: Int): Boolean
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    fun onBindViewHolder(item: T, holder: RecyclerView.ViewHolder)
}