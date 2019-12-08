package com.tushar.todosample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.tushar.todosample.entity.Todo
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoListAdapter(private val onClickTodo: (Todo) -> Unit) :
    ListAdapter<Todo, BaseViewHolder<Todo>>(DiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Todo> =
        TripViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            ), onClickTodo
        )

    override fun onBindViewHolder(holder: BaseViewHolder<Todo>, position: Int) =
        holder.onBind(getItem(position))
}

class DiffUtil : DiffUtil.ItemCallback<Todo>() {
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean = oldItem == newItem
}

class TripViewHolder(
    itemView: View,
    private val onClickTodo: (Todo) -> Unit
) : BaseViewHolder<Todo>(itemView) {

    override fun onBind(item: Todo) {
        itemView.apply {
            tvTitle.text = context.getString(R.string.task_title, item.taskTitle)
            tvDueDate.text = context.getString(R.string.task_due_date, item.dueDate)
            setOnClickListener {
                onClickTodo.invoke(item)
            }
        }
    }
}