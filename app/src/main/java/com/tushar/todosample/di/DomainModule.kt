package com.tushar.todosample.di

import com.tushar.todosample.usecase.DeleteTodoUseCase
import com.tushar.todosample.usecase.GetAllTodoUseCase
import com.tushar.todosample.usecase.InsertTodoUseCase
import com.tushar.todosample.usecase.UpdateTodoUseCase
import org.koin.dsl.module.module
import org.koin.experimental.builder.single

val domainModule = module {
    single<GetAllTodoUseCase>()
    single<InsertTodoUseCase>()
    single<UpdateTodoUseCase>()
    single<DeleteTodoUseCase>()
}