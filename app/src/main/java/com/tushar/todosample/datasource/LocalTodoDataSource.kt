package com.tushar.todosample.datasource

import com.tushar.todosample.entity.Todo

interface LocalTodoDataSource {

    suspend fun getAll(): List<Todo>

    suspend fun insert(todo: Todo)

    suspend fun update(todo: Todo)

    suspend fun delete(todoId: Int)
}