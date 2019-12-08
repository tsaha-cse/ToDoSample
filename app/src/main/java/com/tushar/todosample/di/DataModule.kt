package com.tushar.todosample.di

import com.tushar.todosample.datasource.LocalTodoDataSource
import com.tushar.todosample.datasource.RoomTodoDataSourceImpl
import com.tushar.todosample.db.TodoDb
import com.tushar.todosample.repository.TodoRepository
import com.tushar.todosample.repository.TodoRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import org.koin.experimental.builder.singleBy

val dataModule = module {
    single {
        TodoDb.getDatabase(androidContext()).todoDao()
    }
    singleBy<LocalTodoDataSource, RoomTodoDataSourceImpl>()
    singleBy<TodoRepository, TodoRepositoryImpl>()
}