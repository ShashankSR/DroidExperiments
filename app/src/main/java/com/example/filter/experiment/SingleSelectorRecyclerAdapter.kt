package com.example.filter.experiment

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.practo.droid.common.ui.multiselector.SingleSelector
import java.util.*

class SingleSelectorRecyclerAdapter<T>(
        private val data: ArrayList<T>,
        private val singleSelector: SingleSelector = SingleSelector())
    : RecyclerView.Adapter<GenericSelectableViewHolder<T>>() {

    lateinit var onSelectionChanged: (T?) -> Unit

    private lateinit var itemClickListener: (T?) -> Unit
    private lateinit var creator: (ViewGroup) -> GenericSelectableViewHolder<T>
    private lateinit var lastItem: (ViewGroup) -> GenericSelectableViewHolder<T>

    fun setItemClickListener(listener: (T?) -> Unit) {
        itemClickListener = listener
    }

    fun setItemCreator(creator: (ViewGroup) -> GenericSelectableViewHolder<T>) {
        this.creator = creator
    }

    fun setLastItemCreator(creator: (ViewGroup) -> GenericSelectableViewHolder<T>) {
        this.lastItem = creator
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericSelectableViewHolder<T> {
        return if (viewType == MORE_VIEW_TYPE) {
            creator(parent)
        } else {
            lastItem(parent)
        }
    }

    override fun onBindViewHolder(holder: GenericSelectableViewHolder<T>, position: Int) {
        holder.bind(data.get(position))
        if (position == itemCount) {
            holder.itemView.setOnClickListener {
                itemClickListener(null)
            }
        } else {
            singleSelector.bindHolder(holder, position, holder.itemId)
            if (selectedPosition == position) {
                singleSelector.setSelected(holder, true)
            } else {
                singleSelector.setSelected(holder, false)
            }
            holder.itemView.setOnClickListener {
                itemClickListener(data!![holder.layoutPosition])
            }
        }
    }

    var selectedPosition = -1

    companion object {
        const val MORE_VIEW_TYPE = 1
        const val ITEM_VIEW = 0
    }

    init {
        singleSelector.isSelectable = true
    }

    override fun getItemViewType(position: Int): Int = if (position == data.size) MORE_VIEW_TYPE else ITEM_VIEW

    override fun getItemCount(): Int = data.size + 1

    fun setSelected(value: T, position: Int) {
        val isValueRemove = data.remove(value)
        if (!isValueRemove) {
            data.removeAt(data.size - 1)
        }
        data.add(0, value)
        singleSelector.setSelected(0, 0, false)
        notifyItemChanged(0)
        notifyItemRemoved(position)
        notifyItemInserted(0)
        selectedPosition = 0
        onSelectionChanged(value)
    }

    fun getSelected(): T? =
            if (singleSelector.selectedPosition >= 0 && singleSelector.selectedPosition < data.size)
                data[singleSelector.selectedPosition]
            else
                null

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}
