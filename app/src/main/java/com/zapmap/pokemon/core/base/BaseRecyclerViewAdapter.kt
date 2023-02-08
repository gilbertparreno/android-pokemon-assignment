package com.zapmap.pokemon.core.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerViewAdapter<T>(
    open val items: MutableList<T> = mutableListOf()
) : RecyclerView.Adapter<BaseRecyclerViewAdapter<T>.ViewHolder>() {

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        onBindViewHolder(holder, items[position], position)
    }

    abstract fun onBindViewHolder(
        viewHolder: ViewHolder,
        item: T,
        position: Int
    )

    override fun getItemCount() = items.size

    inner class ViewHolder(val viewBinding: ViewBinding) : RecyclerView.ViewHolder(viewBinding.root)
}