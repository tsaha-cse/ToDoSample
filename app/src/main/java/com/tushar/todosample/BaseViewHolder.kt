package com.tushar.todosample

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<D>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun onBind(item: D)
}
