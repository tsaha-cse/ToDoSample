package com.tushar.todosample.repository

import com.tushar.todosample.entity.Todo

interface TodoRepository {

    suspend fun getAllTodo(): List<Todo>

    suspend fun insertTodo(todo: Todo)

    suspend fun updateTodo(todo: Todo)

    suspend fun deleteTodo(todoId: Int)
}