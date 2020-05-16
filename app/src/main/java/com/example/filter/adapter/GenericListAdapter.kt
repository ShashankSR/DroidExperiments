package com.example.filter.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import java.util.ArrayList

class GenericListAdapter<T> constructor(
    data: ArrayList<T>?, private val itemClickListener: (T) -> Unit,
    private val creator: (ViewGroup) -> GenericViewHolder<T>)
    : RecyclerView.Adapter<GenericViewHolder<T>>() {
    
    private var data: ArrayList<T>? = null
    
    init {
        if (data != null) {
            this.data = data
        }
    }
    
    fun removeItem(position: Int) {
        data?.removeAt(position)
        notifyItemRemoved(position)
    }
    
    fun getItem(position: Int) = data?.get(position)
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T> = creator(parent)
    
    override fun onBindViewHolder(holder: GenericViewHolder<T>, position: Int) {
        holder.bind(data?.get(position))
        holder.itemView.setOnClickListener {
            itemClickListener(data!![holder.layoutPosition])
        }
    }
    
    override fun getItemCount(): Int {
        return if (data != null) {
            data!!.size
        } else 0
    }
    
    fun setData(newData: ArrayList<T>?) {
        if (newData != null) {
            data = newData
            notifyDataSetChanged()
        }
    }
}