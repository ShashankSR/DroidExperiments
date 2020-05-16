package com.example.filter.experiment

import android.databinding.BindingAdapter
import android.databinding.InverseBindingAdapter
import android.databinding.InverseBindingListener
import com.practo.droid.common.ui.ItemCalculatorView

object ItemCalculatorBindingAttributes {
    @JvmStatic
    @BindingAdapter("count")
    fun bindSetItemCalculator(view: ItemCalculatorView, count: Int) {
        view.itemCount = count
    }

    @JvmStatic
    @InverseBindingAdapter(attribute = "count")
    fun bindGetItemCalcuator(view: ItemCalculatorView): Int = view.itemCount


    @JvmStatic
    @BindingAdapter("countAttrChanged")
    fun bindListeneer(view: ItemCalculatorView, listener: InverseBindingListener) {
        view.setItemCountListener { listener.onChange() }
    }
}