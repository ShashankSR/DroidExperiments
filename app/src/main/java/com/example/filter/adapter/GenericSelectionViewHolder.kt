package com.example.filter.adapter

import android.view.View
import com.practo.droid.common.ui.multiselector.SelectableHolder

abstract class GenericSelectionViewHolder<U> constructor(itemView: View) : GenericViewHolder<U>(itemView), SelectableHolder