package ru.effect.coursesapp.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(
    private val delegates: List<DelegateAdapter<*>>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<Any>()

    fun setItems(newItems: List<Any>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return delegates.indexOfFirst { it.isForViewType(items, position) }
            .takeIf { it!=-1 } ?: error("No delegate found for position $position")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegates[viewType].onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val delegate = delegates[getItemViewType(position)]
        val item = items[position]
        @Suppress("UNCHECKED_CAST")
        (delegate as DelegateAdapter<Any>).onBindViewHolder(item, holder)
    }

    override fun getItemCount() = items.size
}