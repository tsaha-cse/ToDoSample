package com.tushar.todosample

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tushar.todosample.entity.Todo
import com.tushar.todosample.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val todoViewModel: TodoViewModel by viewModel()

    private lateinit var todoListAdapter: TodoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycle.addObserver(todoViewModel)

        with(rvTodo) {
            layoutManager = LinearLayoutManager(this@MainActivity).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            todoListAdapter = TodoListAdapter(::showUpdateDialog)
            adapter = todoListAdapter
        }

        todoViewModel.todoSubscription().observe(this, Observer {
            todoListAdapter.submitList(it)
        })

        todoViewModel.statusSubscription().observe(this, Observer {
            it.message?.let { message ->
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            }
        })

        fabAddTodo?.setOnClickListener {
            showInsertTodoDialog()
        }
    }

    private fun showUpdateDialog(todo: Todo) {
        with(AlertDialog.Builder(this)) {
            val dialogView = View.inflate(
                this@MainActivity,
                R.layout.layout_dialog_edit_text,
                null
            )
            setView(dialogView)
            setTitle(R.string.title_update_task)

            val etTaskTitle = dialogView?.findViewById(R.id.etTaskTitle) as? EditText
            val etTaskDueDate = dialogView?.findViewById(R.id.etTaskDueDate) as? EditText
            etTaskTitle?.setText(todo.taskTitle)
            etTaskDueDate?.setText(todo.dueDate)

            setPositiveButton(R.string.btn_dialog_update) { _, _ ->
                todoViewModel.onClickUpdateTodo(
                    { todo.id },
                    { etTaskTitle?.text?.toString() },
                    { etTaskDueDate?.text?.toString() }
                )
            }
            setNegativeButton(R.string.btn_dialog_delete) { _, _ ->
                todoViewModel.onClickDeleteTodo(
                    { todo.id },
                    { etTaskTitle?.text?.toString() },
                    { etTaskDueDate?.text?.toString() }
                )
            }
            create().show()
        }
    }

    private fun showInsertTodoDialog() {
        with(AlertDialog.Builder(this)) {
            val dialogView = View.inflate(
                this@MainActivity,
                R.layout.layout_dialog_edit_text,
                null
            )
            setView(dialogView)
            val etTaskTitle = dialogView?.findViewById(R.id.etTaskTitle) as? EditText
            val etTaskDueDate = dialogView?.findViewById(R.id.etTaskDueDate) as? EditText
            setTitle(R.string.title_create_task)
            setPositiveButton(android.R.string.ok) { _, _ ->
                todoViewModel.onClickSubmitTodo(
                    { etTaskTitle?.text?.toString() },
                    { etTaskDueDate?.text?.toString() }
                )
            }
            create().show()
        }
    }
}
