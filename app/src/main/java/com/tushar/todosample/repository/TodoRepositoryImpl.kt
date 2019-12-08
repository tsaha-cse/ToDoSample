package com.tushar.todosample.repository

import com.tushar.todosample.datasource.LocalTodoDataSource
import com.tushar.todosample.entity.Todo

class TodoRepositoryImpl(private val localTodoDataSource: LocalTodoDataSource) : TodoRepository {

    override suspend fun getAllTodo(): List<Todo> = localTodoDataSource.getAll()

    override suspend fun insertTodo(todo: Todo) {
        localTodoDataSource.insert(todo)
    }

    override suspend fun updateTodo(todo: Todo) {
        localTodoDataSource.update(todo)
    }

    override suspend fun deleteTodo(todoId: Int) = localTodoDataSource.delete(todoId)
}