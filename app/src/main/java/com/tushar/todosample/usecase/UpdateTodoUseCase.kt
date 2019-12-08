package com.tushar.todosample.usecase

import com.tushar.todosample.entity.Todo
import com.tushar.todosample.repository.TodoRepository
import com.tushar.todosample.usecase.base.BaseUseCase

class UpdateTodoUseCase(
    private val todoRepository: TodoRepository
) : BaseUseCase<Todo, Todo>() {

    override suspend fun build(param: Todo): Todo {
        todoRepository.updateTodo(param)
        return param
    }
}