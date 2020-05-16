package com.example.filter.experiment

import android.support.v7.widget.RecyclerView
import android.view.View
import com.practo.droid.common.ui.multiselector.SelectableHolder

abstract class GenericSelectableViewHolder<U> protected constructor(itemView: View) : RecyclerView.ViewHolder(itemView), SelectableHolder {

    abstract fun bind(data: U?)
}