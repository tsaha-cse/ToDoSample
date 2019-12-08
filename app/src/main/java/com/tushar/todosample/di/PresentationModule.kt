package com.tushar.todosample.di

import com.tushar.todosample.viewmodel.TodoViewModel
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module

val presentationModule = module {
    viewModel<TodoViewModel>()
}