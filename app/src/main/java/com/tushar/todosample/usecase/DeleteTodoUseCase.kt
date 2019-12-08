package com.tushar.todosample.usecase

import com.tushar.todosample.repository.TodoRepository
import com.tushar.todosample.usecase.base.BaseUseCase

class DeleteTodoUseCase(
    private val todoRepository: TodoRepository
) : BaseUseCase<Int, Boolean>() {

    override suspend fun build(param: Int): Boolean {
        todoRepository.deleteTodo(param)
        return true
    }
}