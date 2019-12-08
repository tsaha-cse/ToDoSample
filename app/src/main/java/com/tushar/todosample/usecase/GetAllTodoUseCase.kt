package com.tushar.todosample.usecase

import com.tushar.todosample.entity.Todo
import com.tushar.todosample.repository.TodoRepository
import com.tushar.todosample.usecase.base.BaseUseCase

class GetAllTodoUseCase(
    private val todoRepository: TodoRepository
) : BaseUseCase<Unit?, List<Todo>>() {

    override suspend fun build(param: Unit?): List<Todo> = todoRepository.getAllTodo()
}