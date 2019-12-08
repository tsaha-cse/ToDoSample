package com.tushar.todosample.datasource

import be.maximedupierreux.todoapplication.android.dao.TodoDao
import com.tushar.todosample.entity.Todo

class RoomTodoDataSourceImpl(private val todoDao: TodoDao) : LocalTodoDataSource {

    override suspend fun getAll() = todoDao.getAll()

    override suspend fun insert(todo: Todo) {
        todoDao.insert(todo)
    }

    override suspend fun update(todo: Todo) {
        todoDao.update(todo)
    }

    override suspend fun delete(todoId: Int) {
        todoDao.delete(todoId)
    }
}