package com.tushar.todosample.viewmodel

import androidx.lifecycle.*
import com.tushar.todosample.entity.Todo
import com.tushar.todosample.usecase.DeleteTodoUseCase
import com.tushar.todosample.usecase.GetAllTodoUseCase
import com.tushar.todosample.usecase.InsertTodoUseCase
import com.tushar.todosample.usecase.UpdateTodoUseCase
import com.tushar.todosample.usecase.base.Result
import kotlinx.coroutines.*
import org.koin.standalone.KoinComponent
import kotlin.coroutines.CoroutineContext

class TodoViewModel(
    private val getAllTodoUseCase: GetAllTodoUseCase,
    private val insertTodoUseCase: InsertTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase
) : ViewModel(), CoroutineScope, KoinComponent, LifecycleObserver {

    private var job = Job()
    override val coroutineContext: CoroutineContext get() = job + Dispatchers.IO

    private val todoListDispatcher: LiveData<List<Todo>> =
        MutableLiveData<List<Todo>>().apply {
            value = emptyList()
        }

    private val statusDispatcher: LiveData<Status> = MutableLiveData<Status>().apply {
        value = Status()
    }

    fun todoSubscription(): LiveData<List<Todo>> = todoListDispatcher

    fun statusSubscription(): LiveData<Status> = statusDispatcher

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        getAllTodo()
    }

    fun onClickSubmitTodo(
        onTitle: () -> String?,
        onDueDate: () -> String?
    ) {
        insertTodo(Todo(taskTitle = onTitle.invoke(), dueDate = onDueDate.invoke()))
    }

    fun onClickUpdateTodo(
        onId: () -> Int,
        onTitle: () -> String?,
        onDueDate: () -> String?
    ) {
        updateTodo(Todo(id = onId(), taskTitle = onTitle.invoke(), dueDate = onDueDate.invoke()))
    }

    fun onClickDeleteTodo(
        onId: () -> Int,
        onTitle: () -> String?,
        onDueDate: () -> String?
    ) {
        deleteTodo(Todo(id = onId(), taskTitle = onTitle.invoke(), dueDate = onDueDate.invoke()).id)
    }

    private fun getAllTodo() = launch {
        with(getAllTodoUseCase(null)) {
            when (this) {
                is Result.Success -> {
                    todoListDispatcher.postVal(value)
                    if (value.isEmpty()) {
                        statusDispatcher.postVal(Status(MSG_GENERIC_EMPTY_TASK, false))
                    }
                }

                else -> statusDispatcher.postVal(Status(MSG_GENERIC_ERROR_TASK_LIST, false))
            }
        }
    }

    private fun insertTodo(todo: Todo) = launch {
        with(insertTodoUseCase(todo)) {
            when (this) {
                is Result.Success -> {
                    getAllTodo()
                }
                else -> statusDispatcher.postVal(Status(MSG_ERROR_INSERT))
            }
        }
    }

    private fun updateTodo(todo: Todo) = launch {
        with(updateTodoUseCase(todo)) {
            when (this) {
                is Result.Success -> {
                    getAllTodo()
                }
                else -> statusDispatcher.postVal(Status(MSG_ERROR_UPDATE, false))
            }
        }
    }

    private fun deleteTodo(todoId: Int) = launch {
        with(deleteTodoUseCase(todoId)) {
            when (this) {
                is Result.Success -> {
                    getAllTodo()
                }
                else -> statusDispatcher.postVal(Status(MSG_ERROR_DELETE, false))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }

    companion object {
        private const val MSG_GENERIC_EMPTY_TASK =
            "No TODO task found! Press FAB to insert task"
        private const val MSG_GENERIC_ERROR_TASK_LIST =
            "Something went wrong getting the task list!"
        private const val MSG_ERROR_INSERT = "Something went wrong inserting new task"
        private const val MSG_ERROR_UPDATE = "Something went wrong updating the task"
        private const val MSG_ERROR_DELETE = "Something went wrong deleting the task"
    }
}