package com.example.filter.experiment

import android.content.Context
import android.databinding.BindingAdapter
import android.databinding.InverseBindingAdapter
import android.databinding.InverseBindingListener
import android.support.v7.widget.RecyclerView

/**
 * Experiment Failed. Tried to make a generic Recyeler view for selection but Databinding
 * can't be genric. Have to have a hardcoded data type.
 */
@Suppress("UNCHECKED_CAST")
class SingleSelectorRecyclerView(context: Context) : RecyclerView(context) {

//    companion object {
//        @JvmStatic
//        @BindingAdapter("adapterData")
//        fun <T> bindAdapterData(view: SingleSelectorRecyclerView, list: ArrayList<T>?) {
//            view.updateData(list)
//        }
//
//        @JvmStatic
//        @BindingAdapter(value = ["adapterSelected", "adapterSelectedChanged"], requireAll = true)
//        fun <T> bindAdapterSetSelected(view: SingleSelectorRecyclerView, value: T, listener: InverseBindingListener) {
//            view.setSelected(value)
//            (view.adapter as SingleSelectorRecyclerAdapter<T>).onSelectionChanged = {
//                listener.onChange()
//            }
//        }
//
//        @JvmStatic
//        @InverseBindingAdapter(attribute = "adapterSelected", event = "adapterSelectedChanged")
//        fun <T> bindAdapterGetSelected(view: SingleSelectorRecyclerView): T? = view.getSelected()
//    }

    fun <T> updateData(list: ArrayList<T>?) {
        if (adapter == null && list != null && list.isNotEmpty()) {
            adapter = SingleSelectorRecyclerAdapter(list)
        } else {
            // No update data functionality as of now
        }
    }

    fun <T> setSelected(value: T) {
        if (adapter != null) {
            (adapter as SingleSelectorRecyclerAdapter<T>).setSelected(value, 0)
        }
    }

    fun <T> getSelected(): T? =
            if (adapter != null) {
                (adapter as SingleSelectorRecyclerAdapter<T>).getSelected()
            } else {
                null
            }
}