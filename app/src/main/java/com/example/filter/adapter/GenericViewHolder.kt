package com.example.filter.adapter

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class GenericViewHolder<U> protected constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    /**
     * To bind data to view only
     */
    abstract fun bind(data: U?)
}